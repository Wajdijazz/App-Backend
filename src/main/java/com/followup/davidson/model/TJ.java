package com.followup.davidson.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Entity
@Table(name="tj")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/**
 * TJ est la classe represetant le taux de jour des consultants chez Davidson
 */
public class TJ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tjId;

    private Float   tarif;

    /**
     * La personne affectée à ce taux de jour
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="person_id")
    private Person person;
    /**
     * Le projet affecté à ce taux de jour
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="project_id")
    private Project project;

}
