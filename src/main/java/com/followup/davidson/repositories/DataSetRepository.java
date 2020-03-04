package com.followup.davidson.repositories;

import com.followup.davidson.dto.DatasetDto;
import com.followup.davidson.model.Client;
import com.followup.davidson.model.DataSet;
import com.followup.davidson.model.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataSetRepository extends JpaRepository<DataSet, Long> {
    @Query(value = "select  p.project_id,p.project_name, pe.person_id,pe.first_name,pe.last_name, tj.tarif, count(*) " +
            "from intervention i " +
            "LEFT JOIN project p on i.project_id = p.project_id " +
            "LEFT JOIN person pe on i.person_id = pe.person_id " +
            "LEFT JOIN tj on tj.person_id = pe.person_id and tj.project_id = p.project_id " +
            "WHERE EXTRACT(MONTH FROM date)= 3 " +
            "and EXTRACT(YEAR FROM date)= 2020 " +
            "group by  pe.person_id, p.project_id, tj.tarif",
            nativeQuery = true)
    List<DataSet> getDataSet(int month, int year);




    @Query(value = "select  p.project_id, pe.person_id, tj.tarif, count(*)\n" +
            "from public.intervention i\n" +
            "LEFT JOIN project p on i.project_id = p.project_id\n" +
            "LEFT JOIN person pe on i.person_id = pe.person_id\n" +
            "LEFT JOIN tj on tj.person_id = pe.person_id and tj.project_id = p.project_id\n" +
            "\t\t   group by  pe.person_id, p.project_id, tj.tarif",
            nativeQuery = true)
    List<DataSet> getDataSetForTj();
}
