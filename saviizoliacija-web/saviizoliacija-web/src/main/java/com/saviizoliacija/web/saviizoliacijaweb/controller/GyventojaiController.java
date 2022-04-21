package com.saviizoliacija.web.saviizoliacijaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;

@Controller
public class GyventojaiController {
	@Autowired
	GyventojaiService service;
	
	// http://localhost:8080/list-gyventojai
	@GetMapping("/list-gyventojai")
	public String showGyventojai(ModelMap model) {
		model.put("gyventojai", service.findAll());
		return "list-gyventojai";
	}
	
	// http://localhost:8080/add-gyventoja
		@GetMapping("/add-gyventoja")
		public String showAddPage(ModelMap model) {
			model.addAttribute("gyventojas", new Gyventojai("", ""));
			return "gyventojas";
		}

		// http://localhost:8080/add-gyventoja
		@PostMapping("/add-gyventoja")
		public String add(ModelMap model, @ModelAttribute("gyventojas") Gyventojai p, BindingResult result) {
			if(result.hasErrors()) {
				return "gyventojas";
			}
			service.add(p);
			return "redirect:/list-gyventojai";
		}
		// http://localhost:8080/update-gyventojas/1
		@GetMapping("/update-gyventojas/{gyventojoId}")
		public String showUpdatePage(ModelMap model, @PathVariable int gyventojoId) {
			model.addAttribute("gyventojas", service.findById(gyventojoId));
			return "gyventojas";
		}

		// http://localhost:8080/update-gyventojas/1
		@PostMapping("/update-gyventojas/{gyventojoId}")
		public String update(ModelMap model, @ModelAttribute("gyventojas") Gyventojai p, BindingResult result) {
			if(result.hasErrors()) {
				return "gyventojas";
			}
			service.update(p);
			return "redirect:/list-gyventojai";
		}
//		
		// http://localhost:8080/delete-gyventojas/1
		@GetMapping("/delete-gyventojas/{gyventojoId}")
		public String delete(@PathVariable int gyventojoId) {
			service.deleteById(gyventojoId);
			return "redirect:/list-gyventojai";
		}

}
