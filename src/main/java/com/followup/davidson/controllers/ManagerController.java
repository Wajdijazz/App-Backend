package com.followup.davidson.controllers;


import com.followup.davidson.Routes;
import com.followup.davidson.model.Manager;
import com.followup.davidson.services.IManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Routes.Manger)
public class ManagerController {

    private IManagerService managerService;

    public ManagerController(IManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/")
    public List<Manager> getAllManager() {
        return managerService.findAll();
    }


    @PostMapping("/")
    public Manager createManager(@Valid @RequestBody Manager manager) {
      return   managerService.create(manager);
    }

    @PutMapping("/{managerId}")
    public Manager updateManager(@PathVariable(value = "managerId") Long managerId,
                                                 @Valid @RequestBody Manager manager) {
       return managerService.updateManager(managerId,manager);

    }

    @GetMapping("/{id}")
    public Manager findManagerById(@PathVariable(value = "id") Long managerId) {
        return managerService.findById(managerId);
    }

    @DeleteMapping("/{id}")
    public void deleteManager(@PathVariable(value = "id") Long managerId) {
        managerService.deleteManager(managerId);
    }

}
