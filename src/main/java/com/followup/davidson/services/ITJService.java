package com.followup.davidson.services;

import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;

import java.util.List;

public interface ITJService {


    TjDto create(TjDto tjDto,  Long projectId, Long personId);

    void deleteTj(Long id);

    TjDto updateByProjectAndPerson(TjDto  tjDto,  Long projectId, Long personId);

    Float  findTarifByProject_ProjectIdAndPerson_PersonId(Long projectId, Long personId);




}
