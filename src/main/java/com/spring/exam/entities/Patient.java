package com.spring.exam.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//@Data est l’annotation qui regroupe @Getter, @Setter, @ToString,@EqualsAndHashCode et @RequiredArgsConstructor.
//@EqualsAndHashCode

@Entity
@Table(name = "Patient")
@AllArgsConstructor // généré un constructeur avec tous les attributs.
@Getter
@Setter
@RequiredArgsConstructor //  définit un constructeur avec seulement les attributs non nuls (@NonNull).   ex on final ajouter  final idEtudiant final prenomE final nomE  final option
@ToString //génère une implémentation pour la méthode toString()par défaut.
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPatient")
    long idPatient ;



    @Column(name = "nomPationt")
    String nomPationt ;

    @Column(name = "telephone")
    int telephone;

    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    Date dateNaissance;


    @OneToMany( cascade = {CascadeType.PERSIST}, mappedBy = "patient")
    @JsonIgnore // boucle infini error 500 dans Asign Etudiant
    Set<Rendezvous> rendezvouss;
}
