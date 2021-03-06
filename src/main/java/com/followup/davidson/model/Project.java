package com.followup.davidson.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name="project")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/**
 * Project est la classe represetant un Project chez Davidson
 */
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @NotEmpty
    private String projectName;

    @NotNull
    private boolean isActive;
    /**
     * C'est le client de chaque projet
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manager_id")
    private Manager manager;

}
