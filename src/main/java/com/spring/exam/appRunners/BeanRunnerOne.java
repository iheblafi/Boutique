package com.spring.exam.appRunners;


import com.spring.exam.entities.Clinique;
import com.spring.exam.entities.Medecin;
import com.spring.exam.repositorys.CliniqueRepository;
import com.spring.exam.repositorys.MedecinRepository;
import com.spring.exam.repositorys.PatientRepository;
import com.spring.exam.repositorys.RendezvousRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class BeanRunnerOne implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean One run method Started !!" );
        this.reset( );

    }



    private Date convertCalendarToDate (int Year , int Mounth , int day) {
        Calendar cal = Calendar.getInstance();
        Mounth--;
        cal.set(Year, Mounth, day);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String date1 = format.format(date);
        Date getDate = null;
        try {
            getDate = format.parse(date1);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return  getDate;
    }




    @Autowired // Methode 2
    CliniqueRepository cliniqueRepository;
    @Autowired // Methode 2
    MedecinRepository medecinRepository;
    @Autowired // Methode 2
    PatientRepository patientRepository;
    @Autowired // Methode 2
    RendezvousRepository rendezvousRepository;


    private void reset( ){
        log.info(" Bean One run ->  reset method");
        long id = 2 ;
       Clinique clinique =  cliniqueRepository.findById(id).orElse(null);
        List<Medecin> medecins =  medecinRepository.findMedecinByclinique_(  clinique);
        for (Medecin  medecin : medecins )
        {
            log.info(medecin.getNomMedecin());
        }
    }

}