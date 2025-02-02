package mk.ecode.employees.config;


import jakarta.annotation.PostConstruct;
import mk.ecode.employees.model.EmployeeType;
import mk.ecode.employees.model.Skill;
import mk.ecode.employees.service.EmployeeService;
import mk.ecode.employees.service.SkillService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final SkillService skillService;

    private final EmployeeService service;

    public DataInitializer(SkillService skillService, EmployeeService service) {
        this.skillService = skillService;
        this.service = service;
    }

    private EmployeeType randomizeEventType(int i) {
        if (i % 3 == 0) return EmployeeType.ROLE_CONSULTANT;
        else if (i % 3 == 1) return EmployeeType.ROLE_ADMIN;
        return EmployeeType.ROLE_REGULAR;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.skillService.create("Skill: " + i);
        }

        List<Skill> skills = this.skillService.listAll();
        for (int i = 1; i < 11; i++) {
            this.service.create(
                    "Employee: " + i,
                    "employee." + i + "@ecode.mk",
                    "emp" + i,
                    this.randomizeEventType(i),
                    Stream.of(skills.get((i - 1) % 5).getId(), skills.get((i + 1) % 5).getId()).collect(Collectors.toList()),
                    LocalDate.now().minusYears((i + 1) / 2)
            );
        }
    }
}
