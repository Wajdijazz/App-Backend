package com.followup.davidson.converter;

import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.TJ;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TjConverter implements GenericsConverter<TJ, TjDto> {
    private ProjectConverter projectConverter;
    private PersonConverter personConverter;


    @Override
    public TjDto entityToDto(TJ tj) {
        return TjDto.builder()
                .tjId(tj.getTjId())
                .projectDto(projectConverter.entityToDto(tj.getProject()))
                .personDto(personConverter.entityToDto(tj.getPerson()))
                .tarif(tj.getTarif())
                .build();
    }

    @Override
    public TJ dtoToEntity(TjDto tjDto) {
        return TJ.builder()
                .tjId(tjDto.getTjId())
                .project(projectConverter.dtoToEntity(tjDto.getProjectDto()))
                .person(personConverter.dtoToEntity(tjDto.getPersonDto()))
                .tarif(tjDto.getTarif())
                .build();
    }
}
