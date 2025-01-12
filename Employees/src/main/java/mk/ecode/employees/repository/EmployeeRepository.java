package mk.ecode.employees.repository;

import mk.ecode.employees.model.Employee;
import mk.ecode.employees.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySkillsContainingAndEmploymentDateBefore(Skill skill, LocalDate year);

    List<Employee> findBySkillsContaining(Skill skill);

    List<Employee> findByEmploymentDateBefore(LocalDate year);

    Employee findByEmail(String email);

}
