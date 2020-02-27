package com.followup.davidson.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="intervention")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/**
 * Intervention est la classe represetant une intervention chez Davidson
 */
public class Intervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intervention_id")
    private Long interventionId;

    /**
     * Cette date présente tous les jours de la période d'intervention
     */
    @Column(name ="Date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    /**
     * Cette mode présente les deux partie de la journée PM ou bien AM
     */
    private Mode mode;

    /**
     * C'est la personne affectée à chaque intervention
     */
    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person person;

    /**
     * C'est le projec affecté à chaque intervention
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;


}
