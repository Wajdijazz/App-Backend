package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.dto.ManagerDto;
import com.followup.davidson.services.IManagerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(Routes.MANAGER)
public class ManagerController {

    private IManagerService managerService;

    public ManagerController(IManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/")
    public Collection<ManagerDto> getAllManager() {
        return managerService.findAll();
    }

    @PostMapping("/")
    public ManagerDto createManager(@Valid @RequestBody ManagerDto managerDto) {
        return managerService.createOrUpdate(managerDto);
    }

    @PutMapping("/")
    public ManagerDto updateManager(@Valid @RequestBody ManagerDto managerDto) {
        return managerService.createOrUpdate(managerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable(value = "id") Long managerId) {
        managerService.deleteManager(managerId);
    }

}
