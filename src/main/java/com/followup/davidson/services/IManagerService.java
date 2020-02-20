package com.followup.davidson.services;

import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.model.Manager;

import java.util.List;

public interface IManagerService {
    List<Manager> findAll();

    Manager findById(Long id);

    ManagerDto createOrUpdate(ManagerDto managerDto);

    void deleteManager(Long id);
}
