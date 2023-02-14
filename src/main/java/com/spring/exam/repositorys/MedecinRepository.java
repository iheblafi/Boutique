package com.spring.exam.repositorys;

import com.spring.exam.entities.Clinique;
import com.spring.exam.entities.Medecin;
import com.spring.exam.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {





    Medecin findMedecinBySpecialite(Specialite specialite);
    List<Medecin> findMedecinsBySpecialite(Specialite specialite);




//"SELECT b FROM Book b JOIN b.bookPublishers bp JOIN bp.publisher p WHERE p.id = ?1"
 @Query("select m from Medecin m JOIN m.cliniques c  where  (c = :clinique) ")
 List<Medecin> findMedecinByclinique_(  @Param("clinique") Clinique clinique);

    @Query("select m from Medecin m JOIN m.cliniques c  where  ((c.idClinique = :cliniqueld) and (   m.specialite = :specialite)) ")
    List<Medecin> findMedecinsBySpecialiteAndAndCliniques( @Param("specialite") Specialite specialite ,  @Param("cliniqueld")Long cliniqueld );


}
