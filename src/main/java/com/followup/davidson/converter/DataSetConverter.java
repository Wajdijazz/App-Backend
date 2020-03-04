package com.followup.davidson.converter;

import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.model.Dashboard;
import com.followup.davidson.model.DataSet;
import org.springframework.stereotype.Component;

@Component
public class DataSetConverter implements GenericsConverter<DataSet, DatasetDto> {

    @Override
    public DatasetDto entityToDto(DataSet dataSet) {
        return DatasetDto.builder()
                .personId(dataSet.getPersonId())
                .projectId(dataSet.getProjectId())
                .tarif(dataSet.getTarif())
                .worked(dataSet.getCount())
                .build();
    }

    @Override
    public DataSet dtoToEntity(DatasetDto datasetDto) {
        return DataSet.builder()
                .personId(datasetDto.getPersonId())
                .projectId(datasetDto.getProjectId())
                .tarif(datasetDto.getTarif())
                .count(datasetDto.getWorked())
                .build();
    }
}
