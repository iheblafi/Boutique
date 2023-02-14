package com.spring.exam.Services;


import com.spring.exam.entities.Rendezvous;
import com.spring.exam.repositorys.CliniqueRepository;
import com.spring.exam.repositorys.MedecinRepository;
import com.spring.exam.repositorys.PatientRepository;
import com.spring.exam.repositorys.RendezvousRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//@ConditionalOnProperty(name="Scheduler.enabled", matchIfMissing = true) ajouter Scheduler.enabled = true au propertie

@Slf4j
@Component
@EnableAsync
public class SchedulerService {

    @Autowired // Methode 2
    CliniqueRepository cliniqueRepository;
    @Autowired // Methode 2
    MedecinRepository medecinRepository;
    @Autowired // Methode 2
    PatientRepository patientRepository;
    @Autowired // Methode 2
    RendezvousRepository rendezvousRepository;


    @Async
    @Scheduled(cron = "*/15 * * * * *")
    public void retrieveRendezVous()
    {

        Date instance = new Date() ;
        List<Rendezvous> rendezvouss =  rendezvousRepository.findRendezvousByDateRDVGreaterThanEqual(instance);
        for ( Rendezvous rendezvous :  rendezvouss  ) {
            System.out.println("Liste "+  rendezvous.getDateRDV().toString() +
                    "Medecin "+  rendezvous.getMedecin().getNomMedecin().toString() +
                    "Patient "+  rendezvous.getPatient().getNomPationt().toString());
        }
    }

}
