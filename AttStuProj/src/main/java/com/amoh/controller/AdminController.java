package com.amoh.controller;

import com.amoh.dao.CourseRepository;
import com.amoh.dao.DoctorRepository;
import com.amoh.dao.StudentRepository;
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

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // ** saving doctor data
    @Autowired
    DoctorRepository docRepo;

    @GetMapping("/newdoc") // show form to enter a new doctor
    public String displayDoctorForm(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "tadmin/new-doctor";
    }

    @PostMapping("/savedoc")
    public String saveDoctor(Model model, Doctor doctor){
        // save a doctor to the db
        docRepo.save(doctor);
        return "redirect:/admin/newdoc";
    }


    // ** saving student data
    @Autowired
    StudentRepository stuRepo;

    @GetMapping("/newstu") // show form to enter a new student
    public String displayStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "tadmin/new-student";
    }

    @PostMapping("/savestu")
    public String saveStudent(Model model, Student student){
        // save a student to the db
        stuRepo.save(student);
        return "redirect:/admin/newstu";
    }


    // ** saving course data
    @Autowired
    CourseRepository courRepo;

    @GetMapping("/newcour") // show form to enter a new course
    public String displayCourseForm(Model model){
        Course course = new Course();
        List<Doctor> doctorList = (List<Doctor>) docRepo.findAll();
        List<Student> studentList = (List<Student>) stuRepo.findAll();
        model.addAttribute("course", course);
        model.addAttribute("allDoctors", doctorList);
        model.addAttribute("allStudents", studentList);
        return "tadmin/new-course";
    }

    @PostMapping("/savecour")
    public String saveCourse(Model model, @RequestParam Doctor theDoctor , Course course){
        // save a course to the db
        course.setCour_id(theDoctor.getDoc_id());
        courRepo.save(course);                      // Implementing One to Many and Many to Many
//        courRepo.save(course);
        return "redirect:/admin/newcour";
    }

}
