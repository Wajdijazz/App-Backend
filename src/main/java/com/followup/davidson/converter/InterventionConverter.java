package com.followup.davidson.converter;

import com.followup.davidson.dto.InterventionDto;
import com.followup.davidson.model.Intervention;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InterventionConverter implements GenericsConverter<Intervention, InterventionDto>{

    private PersonConverter personConverter;
    private ProjectConverter projectConverter;

    @Override
    public InterventionDto entityToDto(Intervention intervention) {
        return InterventionDto.builder()
                .date(intervention.getDate())
                .mode(intervention.getMode())
                .person(personConverter.entityToDto(intervention.getPerson()))
                .project(projectConverter.entityToDto(intervention.getProject()))
                .build();
    }

    @Override
    public Intervention dtoToEntity(InterventionDto interventionDto) {
        return Intervention.builder()
                .date(interventionDto.getDate())
                .mode(interventionDto.getMode())
                .person(personConverter.dtoToEntity(interventionDto.getPerson()))
                .project(projectConverter.dtoToEntity(interventionDto.getProject()))
                .build();
    }
}
