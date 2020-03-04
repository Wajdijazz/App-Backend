package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.DataSetConverter;
import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.DataSet;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.repositories.DataSetRepository;
import com.followup.davidson.repositories.InterventionRepository;
import com.followup.davidson.services.IDatasetService;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import com.followup.davidson.services.ITJService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@AllArgsConstructor
@Service
public class DataSetServiceImp implements IDatasetService {

    private DataSetRepository dataSetRepository;
    private DataSetConverter dataSetConverter;


    @Override
    public List<DatasetDto> getDataSet(int month, int year) {
        System.out.println(dataSetRepository.getDataSet(month,year));
        return dataSetConverter.entityListToDtoList(dataSetRepository.getDataSet(month,year));
    }

    @Override
    public List<DatasetDto> getDataSetForTj() {
        return dataSetConverter.entityListToDtoList(dataSetRepository.getDataSetForTj());
    }
}
