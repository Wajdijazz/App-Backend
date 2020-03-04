package com.followup.davidson.services;

import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.model.DataSet;

import java.util.List;

public interface IDatasetService {

    List<DatasetDto> getDataSet(int month, int year);

    List<DatasetDto> getDataSetForTj();


}
