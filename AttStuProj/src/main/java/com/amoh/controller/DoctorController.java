package com.amoh.controller;

import com.amoh.dao.*;
import com.amoh.entity.Attendance;
import com.amoh.entity.Course;
import com.amoh.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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


//    F:\PythonProjects\proJ\AttStuProj\src\main\resources\static\project\doctor.html


    @GetMapping("/")
    public String homeDoctor(){
        return "tdoctor/homedoc";
    }

    @GetMapping("/l")
    public ModelAndView lDoctor(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/login");
        return modelAndView;
    }

    @GetMapping("/d")
    public ModelAndView hDoctor(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/doctor");
        return modelAndView;
    }


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
//        List<Student> studentList = (List<Student>) stuRepo.findAll();
        List<Student> studentList = getListStuFR();

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
//        List<Student> studentList = (List<Student>) stuRepo.findAll();
        List<Student> studentList = getListStuFR();

        for (int i=0; i< studentList.size(); i++) {
            Attendance tempAttendance = new Attendance(secName,studentList.get(i),course);
            attRepo.save(tempAttendance);
        }
//        attRepo.save(attendance);
        return "redirect:/doctor/";
//        return "redirect:/doctor";
    }

//    @GetMapping("/stufrlist")
//    public String redirectToFormAtt(){
//        return "redirect:/doctor/newatt";
//    }

    @GetMapping("/rtfatt")
    public ModelAndView redirectToFormAtt(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tdoctor/clk_todoFR_doctor.html");
        return modelAndView;
    }

//    @GetMapping( "liststufr")
    public List<Student> getListStuFR(){

        List<Student> stuFRlist = new ArrayList<>();
//        System.out.println("EXC.. ");

        List<String> attenceList = new ArrayList<>();
        String s = null;
        try {

            Process p = Runtime.getRuntime().exec("python ./EncodeImages.py");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                String l = s.replaceAll("\\p{P}", "");
                if(attenceList.contains(l)) {
                    System.out.println("Already added ");
                }else {
                    attenceList.add(l);
                }
            }
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
            e.printStackTrace();
        }

        List<String> attList = new ArrayList<>();
        String str =  attenceList.get(0);
        String[] arrOfStr = str.split(" ");
        for (String a : arrOfStr) {
            attList.add(a);
            Student tempStudent = stuRepo.findByStuid(Integer.parseInt(a));
            stuFRlist.add(tempStudent);
            System.out.print(tempStudent.getStu_id()+", ");
        }
        return stuFRlist;
    }

}
