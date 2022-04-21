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
import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.service.SaviizoliacijaService;


@RequestMapping("/saviizoliacija")
@RestController
public class SaviizoliacijaRestController {
	@Autowired
	SaviizoliacijaService service;
	
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Saviizoliacija>> showAllSaviizoliacijosList(){
		return new ResponseEntity(service.findAll(), HttpStatus.OK);
	}
	
	// http://localhost:8080/saviizoliacija/2
	@GetMapping(path="/{id}", produces = {"application/json"})
	public ResponseEntity<Saviizoliacija> saviizoliacijosListById(@PathVariable int id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK); // Spring converts java object to -> JSON
	}
	// http://localhost:8080/saviizoliacija	
	@PostMapping()
	public ResponseEntity<Void> addSaviizoliacijosElement(@RequestBody Saviizoliacija saviizoliacijosElement) {
		Saviizoliacija p = service.add(saviizoliacijosElement); 
			if (p == null)
				return ResponseEntity.noContent().build();
				URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		                .path("/{id}").buildAndExpand(p.getId()).toUri(); 
				
				return ResponseEntity.created(location).build(); 
			}	
	// http://localhost:8080/saviizoliacija/2
	@DeleteMapping(path ="/{id}", produces = {"application/json"})
	public ResponseEntity<Saviizoliacija> deleteSaviizoliacijosElement(@PathVariable(value = "id") long id)
    {
        Saviizoliacija saviioliacijosRecord = service.findById(id);
        service.deleteById(id);
        return ok().body(saviioliacijosRecord);
    }
	// http://localhost:8080/saviizoliacija/1
	@PutMapping(path="/{id}")
    public ResponseEntity<Saviizoliacija> updateSaviizoliacijosElement(@PathVariable(value = "id") long id, @RequestBody Saviizoliacija saviizoliacijosElement)
    {
        Saviizoliacija saviizoliacija = service.findById(id);
        saviizoliacija.setGyventojai(saviizoliacijosElement.getGyventojai());
        saviizoliacija.setData(saviizoliacijosElement.getData());
        saviizoliacija.setSaviizoliacijaON(saviizoliacijosElement.getSaviizoliacijaON());
        service.update(saviizoliacija);
        return ok().body(saviizoliacija);
    }
	
	
	
}
