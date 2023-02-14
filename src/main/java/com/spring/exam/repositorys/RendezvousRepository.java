package com.spring.exam.repositorys;

import com.spring.exam.entities.Medecin;
import com.spring.exam.entities.Rendezvous;
import com.spring.exam.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Long> {




   // getRendevousByCliniqueAndSpeciallte
   public List< Rendezvous > findRendezvousByMedecin(Medecin medecin);

   int countRendezvousByMedecin(Medecin medecin);

   List< Rendezvous > findRendezvousByDateRDVGreaterThanEqual(Date date);

   List< Rendezvous > findRendezvousByDateRDVBetween(Date startDate , Date endDate);

   List< Rendezvous > findRendezvousByMedecinAndDateRDVBetween (Medecin medecin, Date startDate , Date endDate);

   List< Rendezvous > findRendezvousByMedecin_IdMedecinAndDateRDVBetween(long id_medecin, Date startDate , Date endDate);






   @Query("select r from Rendezvous r JOIN r.medecin m JOIN m.cliniques c  where  ((c.idClinique = :cliniqueld) and (   m.specialite = :specialite)) ")
   List<Rendezvous> findRendevousByCliniqueAndSpeciallte(@Param("specialite") Specialite specialite , @Param("cliniqueld")Long cliniqueld );


 //  INSERT INTO table_name
 //  VALUES (value1, value2, value3, ...);
// public void addRDVAndAssignMedAndPatient(@RequestBody Rendezvous rdv, @PathVariable  Long idMedecin, @PathVariable Long idPatient)
//@Query("insert into Rendezvous (dateRDV, remarque) VALUES (:dateRDV, :remarque)")
//List<Rendezvous> addRDVAndAssignMedAndPatient(@Param("dateRDV") Specialite specialite , @Param("remarque")Long cliniqueld );

}
