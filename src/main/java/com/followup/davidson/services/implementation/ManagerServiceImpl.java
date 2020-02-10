package com.followup.davidson.services.implementation;

import com.followup.davidson.model.Manager;
import com.followup.davidson.repositories.ManagerRepository;
import com.followup.davidson.services.IManagerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ManagerServiceImpl implements IManagerService {


    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * Cette methode permet de lister tous les managers de davidsons
     *
     * @return une liste des {@link Manager}
     */
    @Override
    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    /**
     * Cette methode permet de créer et sauvgarder un nouveau manager
     *
     * @param manager
     * @return manager crée
     */
    @Override
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    /**
     * Cette methode permet de retourner un manager par id
     *
     * @param id
     * @return un client
     */
    @Override
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Manager updateManager(Long managerId, Manager manager) {
     Manager managerUp=new Manager().builder()
               .managerId(managerId)
              .firstName(manager.getFirstName())
              .lastName(manager.getLastName())
              .build();
        managerRepository.save(managerUp);
        return managerUp ;
    }

    /**
     * Cette methode permet de supprimer un manager par son id
     *
     * @param id
     */
    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}
