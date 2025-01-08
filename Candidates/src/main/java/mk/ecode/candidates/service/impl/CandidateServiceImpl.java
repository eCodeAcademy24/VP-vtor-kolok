package mk.ecode.candidates.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.candidates.model.Candidate;
import mk.ecode.candidates.model.Gender;
import mk.ecode.candidates.model.exceptions.InvalidCandidateIdException;
import mk.ecode.candidates.repository.CandidateRepository;
import mk.ecode.candidates.service.CandidateService;
import mk.ecode.candidates.service.PartyService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PartyService partyService;

    @Override
    public List<Candidate> listAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElseThrow(InvalidCandidateIdException::new);
    }

    @Override
    public Candidate create(String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        Candidate candidate = new Candidate();

        candidate.setName(name);
        candidate.setBio(bio);
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setGender(gender);
        candidate.setParty(partyService.findById(party));
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate update(Long id, String name, String bio, LocalDate dateOfBirth, Gender gender, Long party) {
        Candidate candidate = findById(id);

        candidate.setName(name);
        candidate.setBio(bio);
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setGender(gender);
        candidate.setParty(partyService.findById(party));

        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate delete(Long id) {
        Candidate candidate = findById(id);
        candidateRepository.delete(candidate);

        return candidate;
    }

    @Override
    public Candidate vote(Long id) {
        Candidate candidate = findById(id);

        candidate.setVotes(candidate.getVotes() + 1);
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> listCandidatesYearsMoreThanAndGender(Integer yearsMoreThan, Gender gender) {
        if (gender != null && yearsMoreThan != null) {
            return candidateRepository.findAllByGenderAndDateOfBirthBefore(
                    gender,
                    LocalDate.now().minusYears(yearsMoreThan)
            );
        } else if (gender != null) {
            return candidateRepository.findAllByGender(gender);
        } else if (yearsMoreThan != null) {
            return candidateRepository.findAllByDateOfBirthBefore(
                    LocalDate.now().minusYears(yearsMoreThan)
            );
        }

        return listAllCandidates();
    }
}
