package com.followup.davidson.services;

import com.followup.davidson.dto.DatasetDto;

public interface IDatasetService {

    DatasetDto getByProject(Long projectId);
    DatasetDto getByProjectPersons();
}
