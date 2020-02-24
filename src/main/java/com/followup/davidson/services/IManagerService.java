package com.followup.davidson.services;

import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.model.Manager;

import java.util.Collection;
import java.util.List;

public interface IManagerService {
    Collection<ManagerDto> findAll();

    ManagerDto findById(Long id);

    ManagerDto createOrUpdate(ManagerDto managerDto);

    void deleteManager(Long id);
}
