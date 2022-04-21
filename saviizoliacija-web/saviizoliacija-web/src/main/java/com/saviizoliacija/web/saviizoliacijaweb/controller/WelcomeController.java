package com.saviizoliacija.web.saviizoliacijaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

		// http://localhost:8080
		@GetMapping("/")
		public String showRootPage(ModelMap model) {
			model.put("name", "ANONYMOUS");
			return "welcome"; 
		}
		// http://localhost:8080/welcome?name=Jonas
		@GetMapping("/welcome")
		public String showWelcomePage(@RequestParam String name, ModelMap model) {
			model.put("name", name);
			return "welcome"; 
		}
		

}
