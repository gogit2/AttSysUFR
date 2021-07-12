package com.amoh;

import com.amoh.dao.StuAttRepository;
import com.amoh.entity.Stuattendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/chome")
public class HomeController {

    @Autowired
    private StuAttRepository stuAttRepository;

    // Home Page
    @GetMapping("/")
    public String welcome()
    {
        return "<html><body>"
                + "<h1>WELCOME</h1>"
                + "<br><h1>CHOME</h1>"
                + "</body></html>";
    }

    // Get All Notes
    @GetMapping("/all")
    public List<Stuattendance> getAllNotes()
    {
        return stuAttRepository.findAll();
    }

    // Get StuAtt by id
    @GetMapping("/all/id/{id}")
    public Stuattendance getStuAttById(
            @PathVariable(value = "id") int id)
    {
        return stuAttRepository.findById(id);
    }

    @GetMapping(value = "all/id/idstu")
    public ModelAndView idstu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("idstu.html");
        return modelAndView;
    }



//    // Get StuAtt by nameId
//    @GetMapping("/all/nameId")
//    public ResponseEntity<List<Stuattendance>> getStuAttByName(@RequestParam String nameId)
//    {
//        return new ResponseEntity<List<Stuattendance>>(stuAttRepository.findByName(nameId), HttpStatus.OK);
//    }

    // Get StuAtt by nameId
    @GetMapping("/all/name/{nameId}")
    public Stuattendance getStuAttByName(
            @PathVariable(value = "nameId") String nameId)
    {
        return stuAttRepository.findByName(nameId);
    }



}
