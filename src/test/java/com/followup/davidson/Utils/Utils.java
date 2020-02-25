package com.followup.davidson.Utils;

import com.followup.davidson.dto.ClientDto;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.dto.PersonDto;
import com.followup.davidson.dto.ProjectDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.Manager;
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

    public static final Manager getManager(Long id, String firstName, String lastName) {
        return Manager.builder()
                .managerId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static final ManagerDto getManagerDto(Long id, String firstName, String lastName) {
        return ManagerDto.builder()
                .managerId(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }


    public static final PersonDto getPersonDto(Long id, String firstName, String lastName, Long managerId,
                                               ManagerDto managerDto) {
        return PersonDto.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .managerId(managerId)
                .managerDto(managerDto)
                .build();
    }

    public static final Person getPerson(Long id, String firstName, String lastName,
                                         Manager manager) {
        return Person.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .manager(manager)
                .build();
    }

    public static final ProjectDto getProjectDto(Long id, String projectName, Long clientId, Long managerId,
                                                 ManagerDto managerDto, ClientDto clientDto) {
        return ProjectDto.builder()
                .projectId(id)
                .projectName(projectName)
                .clientId(clientId)
                .managerId(managerId)
                .managerDto(managerDto)
                .clientDto(clientDto)
                .build();
    }


    public static final Project getProject(Long id, String projectName, Manager manager, Client client) {
        return Project.builder()
                .projectId(id)
                .projectName(projectName)
                .manager(manager)
                .client(client)
                .build();
    }

}
