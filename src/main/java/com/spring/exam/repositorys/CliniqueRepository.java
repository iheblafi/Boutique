package com.spring.exam.repositorys;

import com.spring.exam.entities.Clinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CliniqueRepository extends JpaRepository<Clinique, Long> {


   //findBooksByBookPublishersPublisherId(Long publisherId)


  // List<Clinique> findByMedecins (  );

}
