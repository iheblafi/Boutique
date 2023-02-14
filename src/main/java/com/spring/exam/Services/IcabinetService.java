package com.spring.exam.Services;

import com.spring.exam.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;


public interface IcabinetService {
    List<Clinique> SelectAll ();
    Clinique Insert( Clinique contrat);
    ResponseEntity<Clinique> SelectByID ( int id);
    Clinique selectBy ( int id);
    ResponseEntity<Clinique> update(  Clinique contratDetails);
    ResponseEntity<HttpStatus> delete(  Integer id );
    Medecin addMedecinAndAssignToClinique (Medecin medecin, Long cliniqueld);

    Patient addPatient (Patient patient);
    void addRDVAndAssignMedAndPatient(Rendezvous rdv, Long idMedecin, Long idPatient);

    List< Rendezvous > getRendevousByCliniqueAndSpeciallte( Long cliniqueld , Specialite specialite);

    int getNbrRendezVousMedecin(Long idMedecin);

    int getRevenuMedecin(Date startDate, Date endDate);

    int  getRevenuMedecin( long idMedcin , Date startDate, Date endDate);
}
