package com.amoh.dao;

import com.amoh.entity.Stuattendance;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Configuration
public interface UserRepository extends CrudRepository<Stuattendance,Integer> {

}
