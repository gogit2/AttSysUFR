package com.amoh.dao;

import com.amoh.entity.Stuattendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuAttRepository extends JpaRepository<Stuattendance, Integer> { //CrudRepository<Stuattendance, Integer>,
    Stuattendance findById (int id);
//    List<String> findDistinctLastName(String firstName);
    @Query(value = "SELECT * FROM stuatten WHERE namesId = ?1", nativeQuery = true)
    Stuattendance findByName (String nameId);
    List<Stuattendance> findAll();
    // FindByName
}
