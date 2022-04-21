package com.saviizoliacija.web.saviizoliacijaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.repository.SaviizoliacijaRepositoryInterface;


@Service
public class SaviizoliacijaService {

	@Autowired
    private SaviizoliacijaRepositoryInterface repository;

	public List<Saviizoliacija> findAll(){
		return (List<Saviizoliacija>) repository.findAll();
	}

    public Saviizoliacija findById(long id) {
        return repository.findById(id).get();
    }
  
    public void update (Saviizoliacija gyv) {
    	repository.save(gyv);
    }   
    public Saviizoliacija add(Saviizoliacija gyv) {
    	return repository.save(gyv);
    }
    
    public void deleteById(long id) {
    	repository.deleteById(id);	
    }
    
    public void delete(Saviizoliacija gyv) {
    	repository.delete(gyv);	
    }
    public List<Saviizoliacija> getSaviizoliacijaByData(String data)
    {
        return repository.findByData(data);
    }
    
    public List<Saviizoliacija> getSaviizoliacijaBySaviizoliacijaON(int saviizoliacijaON)
    {
        return this.repository.findBySaviizoliacijaON(saviizoliacijaON);
    }
    public List<Saviizoliacija> getSaviizoliacijaByDataAndSaviizoliacijaON(String data, int saviizoliacijaON)
    {
        return this.repository.findByDataAndSaviizoliacijaON(data, saviizoliacijaON);
    }


  
}
