package com.followup.davidson.services.implementation;

import com.followup.davidson.model.TJ;
import com.followup.davidson.repositories.TJRepository;
import com.followup.davidson.services.ITJService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TJServiceImpl  implements ITJService {
    private TJRepository tjRepository;

    public TJServiceImpl(TJRepository tjRepository) {
        this.tjRepository=tjRepository;
    }

    @Override
    public List<TJ> findAll() {
        return tjRepository.findAll();

    }

    @Override
    public TJ create(TJ tj) {
        return tjRepository.save(tj);    }

    @Override
    public Optional<TJ> findById(Long id) {
        return tjRepository.findById(id);
    }

    @Override
    public void deleteTj(Long id) {
        tjRepository.deleteById(id);
    }
}
