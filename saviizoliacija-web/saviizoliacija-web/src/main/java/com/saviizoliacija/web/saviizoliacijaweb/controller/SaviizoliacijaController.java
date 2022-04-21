package com.saviizoliacija.web.saviizoliacijaweb.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;
import com.saviizoliacija.web.saviizoliacijaweb.service.SaviizoliacijaService;

@Controller
public class SaviizoliacijaController {
	@Autowired
	SaviizoliacijaService service;
	
	// http://localhost:8080/list-saviizoliacija
    @GetMapping("/list-saviizoliacija")
    public String showAll(ModelMap model)
    {
        model.put("saviizoliacija", service.findAll());
        return "list-saviizoliacija";
    }
	
	// http://localhost:8080/add-saviizoliacija
	@GetMapping("/add-saviizoliacija")
	public String showAddPage(ModelMap model) {
		Saviizoliacija saviizoliacija = new Saviizoliacija();
		model.addAttribute("saviizoliacija", saviizoliacija);
		return "saviizoliacija";
	}

	// http://localhost:8080/add-saviizoliacija
	@PostMapping("/add-saviizoliacija")
	public String add(ModelMap model, @ModelAttribute("saviizoliacija") Saviizoliacija p, BindingResult result) {
		if(result.hasErrors()) {
			return "saviizoliacija";
		}
		service.add(p);
		return "redirect:/list-saviizoliacija";
	}

	// http://localhost:8080/update-saviizoliacija/1
	@GetMapping("/update-saviizoliacija/{id}")
	public String showUpdatePage(ModelMap model, @PathVariable int id) {
		model.addAttribute("saviizoliacija", service.findById(id));
		return "saviizoliacija";
	}

	// http://localhost:8080/update-saviizoliacija/1
	@PostMapping("/update-saviizoliacija/{id}")
	public String update(ModelMap model, @ModelAttribute("saviizoliacija") Saviizoliacija p, BindingResult result) {
		if(result.hasErrors()) {
			return "saviizoliacija";
		}
		service.update(p);
		return "redirect:/list-saviizoliacija";
	}
			
	// http://localhost:8080/delete-saviizoliacija/1
	@GetMapping("/delete-saviizoliacija/{id}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "redirect:/list-saviizoliacija";
	}

}
