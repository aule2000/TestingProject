package com.saviizoliacija.web.saviizoliacijaweb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;


@Repository
public interface SaviizoliacijaRepositoryInterface extends CrudRepository<Saviizoliacija,Long>{
	List<Saviizoliacija> findByData(@Param("data") String data);
	Saviizoliacija findOneByData(@Param("data") String data);
	List<Saviizoliacija> findBySaviizoliacijaON(@Param("saviizoliacijaON") int saviizoliacijaON);
	List<Saviizoliacija> findByDataAndSaviizoliacijaON(String data, int saviizoliacijaON);
	
	}

