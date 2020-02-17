package com.followup.davidson.services;

import com.followup.davidson.controllers.InterventionController;
import com.followup.davidson.dto.InterventionDto;
import com.followup.davidson.model.Intervention;
import com.followup.davidson.model.Person;
import com.followup.davidson.model.Project;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IInterventionService {


    Intervention findById(Long id);

    List<Intervention> findAll();

    Object saveInterventions(InterventionDto interventionDto, Long personId, Long projectId);

    void deleteIntervention(Long personId, Long projectId);

    void deleteInterventionHistorique(Long id);

    List<Intervention> findByPersonAndProject(long projectId, long personId);

    Float workedDayByPersonAndProject(long projectId, long personId);

    Float workedDayByPersonAndProjectByMonth(long projectId, long personId, long monthNumber, long yearNumber);
}
