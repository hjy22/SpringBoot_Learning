package com.ltp.gradesubmission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class GradeController {

    List<Grade> studentGrades = Arrays.asList(
        new Grade("Harry","Potions","C-"),
        new Grade("Alex","CS","A"),
        new Grade("Ben","EE","B+")
    );
    
    @GetMapping("/grades")
    public String getGrades(Model model){
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @GetMapping("/shows")
    public String getShows(){
        return "shows";
    }
}
