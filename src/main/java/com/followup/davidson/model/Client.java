package com.followup.davidson.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


import javax.persistence.*;


@Entity
@Table(name="client")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode
/**
 * Client est la classe represetant un client chez Davidson
 */
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="client_id")
    private Long clientId;
    /**
     * clientName presente le nom de l'entreprise ou l organisme
     */
    private String clientName;

    /**
     *  clientContact presente le contact pricipal du client
     */
    private String clientContact;



}
