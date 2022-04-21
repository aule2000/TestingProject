package com.saviizoliacija.web.saviizoliacijaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.repository.GyventojaiRepositoryInterface;


@Service
public class GyventojaiService {

	@Autowired
	private GyventojaiRepositoryInterface repository;
	
	public List<Gyventojai> findAll(){
		return (List<Gyventojai>) repository.findAll();
	}

	  public Gyventojai findById(long id) {
	        return repository.findById(id).get();
	    }
	  
	    public void update (Gyventojai gyv) {
	    	repository.save(gyv);
	    }
    
	    public Gyventojai add(Gyventojai gyv) {
	    	return repository.save(gyv);
	    }
	    
	    public void deleteById(long id) {
	    	repository.deleteById(id);	
	    }
	    
	    public void delete(Gyventojai gyv) {
	    	repository.delete(gyv);	
	    }
	    
	    public List<Gyventojai> getGyventojasByVardas(String vardas)
	    {
	        return repository.findByVardas(vardas);
	    }
	    
	    public List<Gyventojai> getGyventojasByTelNr(String telNr)
	    {
	        return this.repository.findByTelNr(telNr);
	    }
	




}
