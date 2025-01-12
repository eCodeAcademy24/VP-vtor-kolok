package mk.ecode.employees.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.employees.model.Employee;
import mk.ecode.employees.model.EmployeeType;
import mk.ecode.employees.model.Skill;
import mk.ecode.employees.model.exceptions.InvalidEmployeeIdException;
import mk.ecode.employees.repository.EmployeeRepository;
import mk.ecode.employees.service.EmployeeService;
import mk.ecode.employees.service.SkillService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final SkillService skillService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = new Employee();
        List<Skill> skills = skillId.stream()
                .map(skillService::findById)
                .collect(Collectors.toList());

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setType(type);
        employee.setEmploymentDate(employmentDate);
        employee.setSkills(skills);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = findById(id);
        List<Skill> skills = skillId.stream()
                .map(skillService::findById)
                .collect(Collectors.toList());

        employee.setName(name);
        employee.setEmail(email);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setType(type);
        employee.setEmploymentDate(employmentDate);
        employee.setSkills(skills);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);

        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId == null && yearsOfService == null) {
            return employeeRepository.findAll();
        } else if (yearsOfService == null) {
            Skill skill = skillService.findById(skillId);
            return employeeRepository.findBySkillsContaining(skill);
        } else if (skillId == null) {
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            return employeeRepository.findByEmploymentDateBefore(employmentBefore);
        } else {
            LocalDate employmentBefore = LocalDate.now().minusYears(yearsOfService);
            Skill skill = skillService.findById(skillId);
            return employeeRepository.findBySkillsContainingAndEmploymentDateBefore(skill, employmentBefore);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByEmail(username);

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(user.getType()));
    }
}
