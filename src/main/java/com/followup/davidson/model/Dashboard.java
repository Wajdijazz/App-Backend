package com.followup.davidson.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="dashboard")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dashboard_id")
    private Long dashboardId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="person_id")
    private Person person;
    /**
     * Le projet affecté à ce taux de jour
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="project_id")
    private Project project;

    private Float tarif;
    private Float worked_day;
    private float total;
}
