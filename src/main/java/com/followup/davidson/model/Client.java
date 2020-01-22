package com.followup.davidson.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter @Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="client_id")
    private Long clientId;

    private String clientName;


    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Project> projects;
}
