package com.myserver.MySpringProject;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class AdminController {

    @GetMapping("admindashboard")
	public String admindashboardPage(){
		return "AdminLayout/admindashboard";
	}
    
	// @GetMapping("alluser")
	// public String allusersPage(){
	// 	return "AdminLayout/allusers";
	// }

	@Autowired
	JdbcTemplate jdbc;
	@GetMapping("allusers")
	public String users(Model model){
		String query = "select * from user_register where Role != 'Admin'";
		List<Map<String, Object>> usermaster = jdbc.queryForList(query);
		model.addAttribute("usermaster", usermaster);
		return "AdminLayout/allusers";
	}

	

}
