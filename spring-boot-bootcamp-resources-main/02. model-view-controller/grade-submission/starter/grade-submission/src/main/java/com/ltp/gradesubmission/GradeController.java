package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
public class GradeController {

    @GetMapping("/grades")
    public String getGrades(Model model){
        Grade grade = new Grade("Harry","Potions","C-");
        model.addAttribute("grade", grade);
        return "grades";
    }

    @GetMapping("/shows")
    public String getShows(){
        return "shows";
    }
}
