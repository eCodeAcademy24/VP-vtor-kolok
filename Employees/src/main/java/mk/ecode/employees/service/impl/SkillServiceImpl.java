package mk.ecode.employees.service.impl;

import mk.ecode.employees.model.Skill;
import mk.ecode.employees.model.exceptions.InvalidSkillIdException;
import mk.ecode.employees.repository.SkillRepository;
import mk.ecode.employees.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(InvalidSkillIdException::new);
    }

    @Override
    public List<Skill> listAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill create(String name) {
        return skillRepository.save(new Skill(name));
    }
}
