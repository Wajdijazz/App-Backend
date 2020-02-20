package com.followup.davidson.converter;

import com.followup.davidson.dto.TjDto;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import com.followup.davidson.model.TJ;
import com.followup.davidson.services.IPersonService;
import com.followup.davidson.services.IProjectService;
import org.springframework.stereotype.Component;

@Component
public class TjConverter   implements GenericsConverter<TJ, TjDto>{

    private IProjectService projectService;
    private IPersonService personService;

    public TjConverter(IProjectService projectService, IPersonService personService) {
        this.projectService = projectService;
        this.personService = personService;
    }

    @Override
    public TjDto entityToDto(TJ tj) {
     return TjDto.builder()
                .tjId(tj.getTjId())
                .personId(tj.getPerson().getPersonId())
                .projectId(tj.getProject().getProjectId())
                .tarif(tj.getTarif())
                .build();
    }

    @Override
    public TJ dtoToEntity(TjDto tjDto) {
        Project project = projectService.findById(tjDto.getProjectId());
        Person person = personService.findById(tjDto.getPersonId());
        return TJ.builder()
                .tjId(tjDto.getTjId())
                .project(project)
                .person(person)
                .tarif(tjDto.getTarif())
                .build();
    }
}
