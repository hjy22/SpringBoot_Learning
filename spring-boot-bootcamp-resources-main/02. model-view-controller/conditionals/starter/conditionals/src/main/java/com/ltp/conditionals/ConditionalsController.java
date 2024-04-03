package com.ltp.conditionals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class ConditionalsController {

    @GetMapping(value="/")
    public String getMethodName(Model model) {
        model.addAttribute("sales", 30);
        model.addAttribute("product", "table");
        return "conditionals";
    }
    
}
