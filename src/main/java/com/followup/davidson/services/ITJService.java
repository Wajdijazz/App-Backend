package com.followup.davidson.services;

import com.followup.davidson.model.TJ;

import java.util.List;
import java.util.Optional;

public interface ITJService {

    List<TJ> findAll();

    TJ create(TJ tj, Long projectId, Long personId);

    Optional<TJ> findById(Long id);

    TJ updateTj(Long tjId,TJ tj,Long projectId,Long personId);

    void deleteTj(Long id);

    Long findTarif(Long projectId, Long personId);

}
