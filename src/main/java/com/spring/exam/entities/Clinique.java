package com.spring.exam.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Clinique")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString //@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Clinique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClinique")
    long idClinique;

    @Column(name = "nomClinique")
    String nomClinique ;

    @Column(name = "adresse")
    String adresse ;

    @Column(name = "telephone")
    int telephone;


    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "cliniques_medecins",joinColumns = { @JoinColumn(name = "id_clinique") },inverseJoinColumns = { @JoinColumn(name = "id_medecin") })
    Set<Medecin> medecins = new HashSet<Medecin>();// @JsonIgnore
}
