package com.followup.davidson.services;

import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;

import java.util.List;

public interface ITJService {

    List<TJ> findAll();

    TjDto create(TjDto tjDto);

    TJ findById(Long id);


    void deleteTj(Long id);

    TjDto updateByProjectAndPerson(TjDto  tjDto);

    Float  findTarifByProject_ProjectIdAndPerson_PersonId(Long projectId, Long personId);

    List<TJ> findByProject_ProjectId(long projectId);


}
