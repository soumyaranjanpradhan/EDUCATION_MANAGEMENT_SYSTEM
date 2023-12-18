package com.myserver.MySpringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class FacultyController {

    @GetMapping("/FacultyDashboard")
    public String facultydashboardPage(){
        return "FacultyLayout/FacultyDashboard";
    }
    @Autowired
	JdbcTemplate jdbc;

	@PostMapping("/FacultyDashboard")
	public String register(
	@RequestParam("question") String question,
	@RequestParam("option1") String option1,
	@RequestParam("option2") String option2, 
	@RequestParam("option3") String option3, 
	@RequestParam("option4") String option4,
    @RequestParam("ans") String ans, Model model ){
	// 	String query =("insert into user_register(name,email,password,mobile,role) values(?,?,?,?,?)");
	// jdbc.update(query, name, email, mobile, password, role);
		jdbc.execute("insert into question(question,option1,option2,option3,option4,ans) values('"+question+"','"+option1+"','"+option2+"','"+option3+"','"+option4+"','"+ans+"')");

	model.addAttribute("message", "register Success");
		return"FacultyDashboard";
	}
    
}
