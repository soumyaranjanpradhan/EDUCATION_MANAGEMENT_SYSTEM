package com.myserver.MySpringProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;


@SpringBootApplication
@Controller
public class MySpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringProjectApplication.class, args);
	}

	@GetMapping("/index")
	public String index() {
	  return "index";
	}
	@GetMapping("/register")
	public String register_page(){
		return"register";
	}
		
	@Autowired
	JdbcTemplate jdbc;

	@PostMapping("/register")
	public String register(
	@RequestParam("name") String name,
	@RequestParam("email") String email,
	@RequestParam("password") String password, 
	@RequestParam("mobile") String mobile, 
	@RequestParam("role") String role, Model model ){
	// 	String query =("insert into user_register(name,email,password,mobile,role) values(?,?,?,?,?)");
	// jdbc.update(query, name, email, mobile, password, role);
		jdbc.execute("insert into user_master(name,email,password,mobile,role) values('"+name+"','"+email+"','"+password+"','"+mobile+"','"+role+"')");

	model.addAttribute("message", "register Success");
		return"register";
	}
	@GetMapping("/login")
	public  String login_page(){
		return"login";
	}
	@PostMapping("/login")
	public String login_check(

			@RequestParam("email") String email, 
			@RequestParam("password") String password, Model model, HttpSession session){
					System.out.println("Login Call");
						// collectionframework
						ArrayList<String> arraylist = new ArrayList<String>();
					List<String> list = jdbc.query("select * from user_master where Email = '"+email+"' and Password = '"+password+"'", new RowMapper<String>()

				{
				public String mapRow(ResultSet rs, int rowNum) throws SQLException{
				arraylist.add(rs.getString(1));
				arraylist.add(rs.getString(2));
				arraylist.add(rs.getString(3));
				arraylist.add(rs.getString(4));
				arraylist.add(rs.getString(5));
				arraylist.add(rs.getString(6));
				return "";
				}
			}
		);

			if (arraylist.isEmpty()) {
					model.addAttribute("message", "Invalid Details.");
						return "login";
				}else{
						session.setAttribute("name", arraylist.get(1));
						session.setAttribute("email", arraylist.get(2));
						if ((arraylist.get(5)).equalsIgnoreCase("Admin")) {
							return "admindashboard";
					}
							else if((arraylist.get(5)).equalsIgnoreCase("Student")) {
							//model.addAttribute("sms", "Welcome To Student Dashboard Page.");
								return "StudentDashboard";
					}
							else if((arraylist.get(5)).equalsIgnoreCase("Faculty")) {
							return "FacultyDashboard";
						}
						else{
						return "login";
						}
					}
				}
			}
