package com.followup.davidson.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SecondaryTable(name = "dashboard", pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"))

/**
 * Person est la classe represetant un consultant chez Davidson
 */
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="person_id")

    private Long personId;
    /**
     * firstName presente le nom de consultant
     */
    @NotEmpty
    private String firstName;
    /**
     * firstName presente le prénom de consultant
     */
    @NotEmpty
    private String lastName;

    @NotNull
    private boolean isActive;

    /**
     * manager presente le manager de chaque consultant
     */

   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name="manager_id")
  private Manager manager;

}
