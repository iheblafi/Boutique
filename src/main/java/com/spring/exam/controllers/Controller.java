package com.spring.exam.controllers;


import com.spring.exam.entities.*;
import com.spring.exam.repositorys.CliniqueRepository;
import com.spring.exam.repositorys.MedecinRepository;
import com.spring.exam.repositorys.PatientRepository;
import com.spring.exam.repositorys.RendezvousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequestMapping("/controller")
public class Controller {






    @Autowired // Methode 2
    CliniqueRepository cliniqueRepository;
    @Autowired // Methode 2
    MedecinRepository medecinRepository;
    @Autowired // Methode 2
    PatientRepository patientRepository;
    @Autowired // Methode 2
    RendezvousRepository rendezvousRepository;










    @PostMapping("/addClinique")
    public Clinique addClinique (@RequestBody Clinique clinique){  return cliniqueRepository.save(clinique);}





    @PostMapping("/addMedecinAndAssignToClinique/{cliniqueld}")
    public Medecin addMedecinAndAssignToClinique (@RequestBody  Medecin medecin, @PathVariable Long cliniqueld){
        Clinique clinique = cliniqueRepository.findById(cliniqueld).orElse(null);
        Medecin NewMedecin  = medecinRepository.save(medecin);
        clinique.getMedecins().add(NewMedecin);
        cliniqueRepository.save(clinique);
        return medecinRepository.findById(NewMedecin.getIdMedecin()).orElse(null);
    }


    @PostMapping("/addPatient")
    public Patient addPatient (@RequestBody Patient patient){  return patientRepository.save(patient);}



    @PostMapping("/addRDVAndAssignMedAndPatient/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody Rendezvous rdv, @PathVariable  Long idMedecin, @PathVariable Long idPatient)
    {
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);
        Patient patient = patientRepository.findById(idPatient).orElse(null);
        rdv.setPatient(patient);
        rdv.setMedecin(medecin);
        rendezvousRepository.save(rdv);

    }


    @GetMapping("/addRDVAndAssignMedAndPatient/{cliniqueld}/{specialite}")
    List< Rendezvous > getRendevousByCliniqueAndSpeciallte(@PathVariable Long cliniqueld , @PathVariable Specialite specialite){
        List< Rendezvous > rendezvous = new ArrayList<Rendezvous>();
        rendezvous =  rendezvousRepository.findRendevousByCliniqueAndSpeciallte(specialite ,cliniqueld );
        return  rendezvous ;
    }

    @GetMapping("/getNbrRendezVousMedecin/{idMedecin}")
    int getNbrRendezVousMedecin(@PathVariable Long idMedecin){
        Medecin  medecin =  medecinRepository.findById(idMedecin).orElse(null);
        return rendezvousRepository.countRendezvousByMedecin(medecin);}



    @GetMapping("/getRevenuMedecin/{startDate}/{endDate}/{idMedcin}")
    int getRevenuMedecin(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                         @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                         @PathVariable("idMedcin") long idMedcin)
      {  int Revenu = 0 ;

        List< Rendezvous > rendezvouss = rendezvousRepository.findRendezvousByDateRDVBetween( startDate,  endDate);
        for ( Rendezvous rendezvous :  rendezvouss   )
        {
            Revenu +=   rendezvous.getMedecin().getPrixConsultation() ;
        }
        return  Revenu ;}



}





