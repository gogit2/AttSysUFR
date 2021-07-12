package com.amoh.controller;

import com.amoh.dao.*;
import com.amoh.entity.Attendance;
import com.amoh.entity.Course;
import com.amoh.entity.Doctor;
import com.amoh.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorRepository docRepo;

    @Autowired
    AttendanceRepository attRepo;

    @Autowired
    CourseRepository courRepo;

    @Autowired
    StudentRepository stuRepo;

    @GetMapping("/newatt") // show form to enter a new doctor
    public String displayAttendancePage(Model model){
        Attendance attendance = new Attendance();
        List<Attendance> allAttendances = (List<Attendance>) attRepo.findAll();
        List<String> secList = new ArrayList<>();
        allAttendances.get(1).getSection();
        for (Attendance att : allAttendances) {
            if (!secList.contains(att.getSection()))
                    secList.add(att.getSection());
        }
        List<Course> courseList = (List<Course>) courRepo.findAll();
        List<Student> studentList = (List<Student>) stuRepo.findAll();

        model.addAttribute("attendance",attendance);
        model.addAttribute("attendances",allAttendances);
        model.addAttribute("sections",secList);
        model.addAttribute("courses",courseList);
        model.addAttribute("students",studentList);
        return "tdoctor/att_doctor";
    }

    @PostMapping("/saveatt")
    public String saveAttendancePage(Model model, /**@RequestParam List<Student> studentList,**/ Attendance attendance){
        // save an attendance to the db
        Course course = attendance.getCourse();
        String secName = attendance.getSection();
        List<Student> studentList = (List<Student>) stuRepo.findAll();

        for (int i=0; i< studentList.size(); i++) {
            Attendance tempAttendance = new Attendance(secName,studentList.get(i),course);
            attRepo.save(tempAttendance);
        }
//        attRepo.save(attendance);
        return "redirect:/doctor/newatt";
    }

}
