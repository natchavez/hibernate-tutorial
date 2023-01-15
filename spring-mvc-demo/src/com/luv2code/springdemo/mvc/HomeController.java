package com.luv2code.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// https://stackoverflow.com/questions/16340711/tomcat-http-status-404
@Controller
public class HomeController {

	@RequestMapping("/ ")
	public String showPage() {
		return "main-menu";
	}

}
