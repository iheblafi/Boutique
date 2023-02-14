package com.spring.exam.Services;

import com.spring.exam.entities.*;
import com.spring.exam.repositorys.CliniqueRepository;
import com.spring.exam.repositorys.MedecinRepository;
import com.spring.exam.repositorys.PatientRepository;
import com.spring.exam.repositorys.RendezvousRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//on peut utilise @RequiredArgsConstructor // Methode 1  comme injection de depondance  @Autowired // Methode 2


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class cabinetService implements IcabinetService {


    @Autowired // Methode 2
     CliniqueRepository cliniqueRepository;
    @Autowired // Methode 2
     MedecinRepository medecinRepository;
    @Autowired // Methode 2
    PatientRepository patientRepository;
    @Autowired // Methode 2
    RendezvousRepository rendezvousRepository;

    @Override
    public List<Clinique> SelectAll() {
        return null;
    }

    @Override
    public Clinique Insert(Clinique clinique) {
        return cliniqueRepository.save(clinique);
    }

    @Override
    public Medecin addMedecinAndAssignToClinique (Medecin medecin, Long cliniqueld){
        Clinique clinique = cliniqueRepository.findById(cliniqueld).orElse(null);
        Medecin NewMedecin  = medecinRepository.save(medecin);
        clinique.getMedecins().add(NewMedecin);
        cliniqueRepository.save(clinique);
        return medecinRepository.findById(NewMedecin.getIdMedecin()).orElse(null);
    }



    @Override
    public Patient addPatient ( Patient patient){  return patientRepository.save(patient);}


    @Override
    public void addRDVAndAssignMedAndPatient(Rendezvous rdv, Long idMedecin, Long idPatient)
    {
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);
        Patient patient = patientRepository.findById(idPatient).orElse(null);
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        rendezvousRepository.save(rdv);
    }

    @Override
    public List< Rendezvous > getRendevousByCliniqueAndSpeciallte( Long cliniqueld , Specialite specialite)
    {
        List< Rendezvous > rendezvous = new ArrayList<Rendezvous>();
    /*   Clinique  clinique =  cliniqueRepository.findById(cliniqueld).orElse(null);
        List<Medecin> medecins =  medecinRepository.findMedecinsBySpecialite( specialite);

        for (Medecin  medecin : medecins )
        {
           for ( Clinique clinique_ :  medecin.getCliniques() )
           {
               if ( clinique_  ==  clinique ) {

                   rendezvous =   rendezvousRepository.findRendezvousByMedecin(medecin) ;
               }
           }
        }*/
        rendezvous =  rendezvousRepository.findRendevousByCliniqueAndSpeciallte(specialite ,cliniqueld );

         return  rendezvous ;

    }

    @Override
    public int getRevenuMedecin( Date startDate, Date endDate) {
        int Revenu = 0 ;

        List< Rendezvous > rendezvouss = rendezvousRepository.findRendezvousByDateRDVBetween( startDate,  endDate);
        for ( Rendezvous rendezvous :  rendezvouss   )
            {
              Revenu +=   rendezvous.getMedecin().getPrixConsultation() ;
            }
       return  Revenu ;
    }

    @Override
    public int getRevenuMedecin( long idMedcin , Date startDate, Date endDate) {
        int Revenu = 0 ;
       /* Medecin  medecin =  medecinRepository.findById(idMedcin).orElse(null);
        List< Rendezvous > rendezvouss =
                rendezvousRepository.findRendezvousByMedecinAndDateRDVBetween ( medecin,  startDate ,  endDate);*/
        List< Rendezvous > rendezvouss =
                rendezvousRepository.
                        findRendezvousByMedecin_IdMedecinAndDateRDVBetween( idMedcin ,  startDate,  endDate);

        for ( Rendezvous rendezvous :  rendezvouss   )
            {
              Revenu +=   rendezvous.getMedecin().getPrixConsultation() ;
            }
        return  Revenu ;
    }




    @Override
     public int getNbrRendezVousMedecin(Long idMedecin)
    {     Medecin  medecin =  medecinRepository.findById(idMedecin).orElse(null);
        return rendezvousRepository.countRendezvousByMedecin(medecin); }

    @Override
    public ResponseEntity<Clinique> SelectByID(int id) {
        return null;
    }

    @Override
    public Clinique selectBy(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Clinique> update(Clinique contratDetails) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Integer id) {
        return null;
    }



}