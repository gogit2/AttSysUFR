package com.amoh.dao;

import com.amoh.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT * FROM student WHERE namestu_id = ?1", nativeQuery = true)
    Student findByStuid(int nameId);
}
