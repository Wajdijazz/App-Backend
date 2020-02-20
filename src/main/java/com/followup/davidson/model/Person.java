package com.followup.davidson.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
/**
 * Person est la classe represetant un consultant chez Davidson
 */
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    /**
     * manager presente le manager de chaque consultant
     */

   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name="manager_id")
  private Manager manager;

}
