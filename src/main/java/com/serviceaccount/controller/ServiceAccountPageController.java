package com.serviceaccount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceAccountPageController {
	
    @RequestMapping("/")
	public String home() {
		return  "index.jsp";
	}
	

}
