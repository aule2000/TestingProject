package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;


@RequestMapping("/gyventojai")
@RestController
public class GyventojaiRestController {
	@Autowired
	GyventojaiService service;

		// http://localhost:8080/gyventojai
	@GetMapping(produces = {"application/json"}) 
		public ResponseEntity<List<Gyventojai>> showAllGyventojuList() {
			return new ResponseEntity<>(service.findAll(), HttpStatus.OK); // Spring converts java object to -> JSON
		}
		
		// http://localhost:8080/gyventojai/2
		@GetMapping(path="/{gyventojasId}", produces = {"application/json"})
		public ResponseEntity<Gyventojai> GyventojasById(@PathVariable int gyventojasId) {
			return new ResponseEntity<>(service.findById(gyventojasId), HttpStatus.OK); // Spring converts java object to -> JSON
		}
	// POST request
		// http://localhost:8080/gyventojai	
		@PostMapping()
		public ResponseEntity<Void> addGyventoja(@RequestBody Gyventojai newGyventojas) {
			Gyventojai p = service.add(newGyventojas); 

			if (p == null)
				return ResponseEntity.noContent().build();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}").buildAndExpand(p.getId()).toUri();
			return ResponseEntity.created(location).build(); 
		}	
		
		// http://localhost:8080/gyventojai/1
		 @DeleteMapping(path="/{id}",produces = {"application/json"})
		    public ResponseEntity<Gyventojai> deleteGyventoja(@PathVariable(value = "id") long id)
		    {
		        Gyventojai gyventojas = service.findById(id);
		        service.deleteById(id);
		        return ok().body(gyventojas);
		    }
		 
		// http://localhost:8080/gyventojai/2
		 @PutMapping(path="/{id}")
		    public ResponseEntity<Gyventojai> updateGyventoja(@PathVariable(value = "id") long id, @RequestBody Gyventojai gyventojai)
		    {
		        Gyventojai gyventojas = service.findById(id);
		        gyventojas.setVardas(gyventojai.getVardas());
		        gyventojas.setTelNr(gyventojai.getTelNr());
		        service.update(gyventojas);
		        return ok().body(gyventojas);
		    }

}
