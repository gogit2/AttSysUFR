package com.amoh.controller;

import com.amoh.dao.DoctorRepository;
import com.amoh.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    // ** saving doctor data
    @Autowired
    DoctorRepository docRepo;

    @GetMapping("/newdoc") // show form to enter a new doctor
    public String displayDoctorForm(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "doctor";
    }

    @PostMapping("/savedoc")
    public String saveDoctor(Model model, Doctor doctor){
        // save a doctor to the db
        docRepo.save(doctor);
        return "redirect:/doctor/newdoc";
    }

}
