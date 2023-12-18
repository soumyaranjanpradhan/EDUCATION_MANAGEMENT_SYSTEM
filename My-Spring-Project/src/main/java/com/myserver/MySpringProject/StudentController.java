package com.myserver.MySpringProject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StudentController {
    
    @GetMapping("studentdashboard")
    public String studentdashboardPage(){
        return "studentdashboard";
    }

}
