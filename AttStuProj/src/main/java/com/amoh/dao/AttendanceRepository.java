package com.amoh.dao;

import com.amoh.entity.Attendance;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<Attendance, Integer> {
}
