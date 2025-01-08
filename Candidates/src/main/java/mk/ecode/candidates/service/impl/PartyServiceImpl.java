package mk.ecode.candidates.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.candidates.model.Party;
import mk.ecode.candidates.model.exceptions.InvalidPartyIdException;
import mk.ecode.candidates.repository.PartyRepository;
import mk.ecode.candidates.service.PartyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepository;

    @Override
    public Party findById(Long id) {
        return partyRepository.findById(id).orElseThrow(InvalidPartyIdException::new);
    }

    @Override
    public List<Party> listAll() {
        return partyRepository.findAll();
    }

    @Override
    public Party create(String name) {
        Party party = new Party();
        party.setName(name);

        return partyRepository.save(party);
    }
}
