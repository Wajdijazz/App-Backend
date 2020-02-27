package com.followup.davidson.Utils;

import com.followup.davidson.dto.*;
import com.followup.davidson.model.*;
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
                                               ManagerDto managerDto, boolean isActive) {
        return PersonDto.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .isActive(isActive)
                .managerId(managerId)
                .managerDto(managerDto)
                .build();
    }

    public static final Person getPerson(Long id, String firstName, String lastName,
                                         Manager manager, boolean isActive) {
        return Person.builder()
                .personId(id)
                .firstName(firstName)
                .lastName(lastName)
                .isActive(isActive)
                .manager(manager)
                .build();
    }

    public static final ProjectDto getProjectDto(Long id, String projectName, Long clientId, Long managerId,
                                                 ManagerDto managerDto, ClientDto clientDto, boolean isActive) {
        return ProjectDto.builder()
                .projectId(id)
                .projectName(projectName)
                .isActive(isActive)
                .clientId(clientId)
                .managerId(managerId)
                .managerDto(managerDto)
                .clientDto(clientDto)
                .build();
    }


    public static final Project getProject(Long id, String projectName, Manager manager, Client client, boolean isActive) {
        return Project.builder()
                .projectId(id)
                .projectName(projectName)
                .isActive(isActive)
                .manager(manager)
                .client(client)
                .build();
    }

    public static final TjDto getTjDto(Long id, float tarif, Long projectId, Long personId, ProjectDto projectDto,
                                       PersonDto personDto) {
        return TjDto.builder()
                .tjId(id)
                .tarif(tarif)
                .projectId(projectId)
                .personId(personId)
                .personDto(personDto)
                .projectDto(projectDto)
                .build();
    }

    public static final TJ getTj(Long id, float tarif, Project project,
                                 Person person) {
        return TJ.builder()
                .tjId(id)
                .tarif(tarif)
                .person(person)
                .project(project)
                .build();
    }

}
