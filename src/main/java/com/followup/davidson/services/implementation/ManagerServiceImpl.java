package com.followup.davidson.services.implementation;

import com.followup.davidson.converter.ManagerConverter;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.exceptions.ApplicationException;
import com.followup.davidson.model.Manager;
import com.followup.davidson.model.Person;
import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.repositories.PersonRepository;
import com.followup.davidson.repositories.ProjectRepository;
import com.followup.davidson.services.IManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class ManagerServiceImpl implements IManagerService {


    private ManagerRepository managerRepository;
    private ManagerConverter managerConverter;
    private ProjectRepository projectRepository;
    private PersonRepository personRepository;


    /**
     * Cette methode permet de lister tous les managers de davidsons
     *
     * @return une liste des {@link Manager}
     */
    @Override
    public Collection<ManagerDto> findAll() {
        return managerConverter.entityListToDtoList(managerRepository.findAll());
    }

    /**
     * Cette methode permet de créer et sauvgarder un nouveau manager
     *
     * @param manager
     * @return manager crée
     */

    /**
     * Cette methode permet de retourner un manager par id
     *
     * @param id
     * @return un client
     */
    @Override
    public ManagerDto findById(Long id) {
        return managerConverter.entityToDto(managerRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("This manager with Id" + id + "not exist")));
    }

    @Override
    public ManagerDto createOrUpdate(ManagerDto managerDto) {
        return  managerConverter.entityToDto(managerRepository.save(managerConverter.dtoToEntity(managerDto)));
    }

    /**
     * Cette methode permet de supprimer un manager par son id
     *
     * @param id
     */
    @Override
    public void deleteManager(Long id) {
        projectRepository.deleteByManager_ManagerId(id);
        personRepository.deleteByManager_ManagerId(id);
        managerRepository.deleteById(id);
    }
}
