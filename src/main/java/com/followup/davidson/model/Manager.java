package com.followup.davidson.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="manager")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/**
 * Manager est la classe represetant un manager chez Davidson
 */
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="manager_id")
    private  Long managerId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;



}
