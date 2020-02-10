package com.followup.davidson.services;

import com.followup.davidson.model.Dataset;

public interface IDatasetService {

    Dataset getByProject(Long projectId);
}
