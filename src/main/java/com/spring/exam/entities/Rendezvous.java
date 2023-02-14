package com.spring.exam.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Rendezvous")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter//@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rendezvous implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRDV")
    long idRDV;

    @Column(name = "dateRDV")
    @Temporal(TemporalType.DATE)
    Date dateRDV;

    @Column(name = "remarque")
    @NotNull
    String remarque;



    @ManyToOne(cascade = {CascadeType.PERSIST})
    Medecin medecin;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    Patient patient;
}
