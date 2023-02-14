package com.spring.exam.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Medecin")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Medecin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedecin")
    long idMedecin;

    @Column(name = "nomMedecin")
    String nomMedecin;

    @Column(name = "specialite")
    @Enumerated(EnumType.STRING)
    Specialite specialite ;

    @Column(name = "telephone")
    int telephone;

    @Column(name = "prixConsultation")
    int prixConsultation;



    @ManyToMany(mappedBy = "medecins",cascade = {CascadeType.PERSIST}) //(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "equipes")//(fetch = FetchType.EAGER, mappedBy = "equipes")
    @JsonIgnore
    Set<Clinique> cliniques = new HashSet<Clinique>();


}
