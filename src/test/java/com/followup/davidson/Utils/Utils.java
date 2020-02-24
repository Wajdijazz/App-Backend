package com.followup.davidson.Utils;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static final Client getClient(Long id, String clientName, String clientContact) {
        return Client.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }

    public static final ClientDto getClientDto(Long id, String clientName, String clientContact) {
        return ClientDto.builder()
                .clientId(id)
                .clientName(clientName)
                .clientContact(clientContact)
                .build();
    }

    private static final PersonDto getPersonDto(Long id, String firstName, String lastName) {
        return PersonDto.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private static final ProjectDto getProjectDto(Long id, String projectName) {
        return ProjectDto.builder()
                .projectId(id)
                .projectName(projectName)
                .build();
    }

    private static final Person getPerson(Long id, String firstName, String lastName) {
        return Person.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private static final Project getProject(Long id, String projectName) {
        return Project.builder()
                .projectId(id)
                .projectName(projectName)
                .build();
    }

}
