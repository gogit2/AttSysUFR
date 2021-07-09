package com.amoh;

import com.amoh.dao.UserRepository;
import com.amoh.entity.Stuattendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/") // value =
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home.html");
        return modelAndView;
    }

    @GetMapping(value = "/h1")
    public @ResponseBody String blaintxt() {
        return ("<html><body><h1>Welcome<br>Home</h1></body></html>");
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Stuattendance> getAllUsers() {
        return userRepository.findAll();
    }

    /* // COMMENTS
    * //
//    @RequestMapping(method = RequestMethod.GET, value = "/h2")
//    public String home2() {
//        return "home.html";
//    }
//
//    @GetMapping(value = "/h3")
//    public String home3() {
//        return "home.html";
//    }

    * */




}
