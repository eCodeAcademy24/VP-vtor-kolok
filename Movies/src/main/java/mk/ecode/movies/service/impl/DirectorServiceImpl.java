package mk.ecode.movies.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.movies.model.Director;
import mk.ecode.movies.model.exceptions.InvalidDirectorIdException;
import mk.ecode.movies.repository.DirectorRepository;
import mk.ecode.movies.service.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id).orElseThrow(InvalidDirectorIdException::new);
    }

    @Override
    public List<Director> listAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director create(String name) {
        Director director = new Director();

        director.setName(name);
        return directorRepository.save(director);
    }
}
