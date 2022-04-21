package com.saviizoliacija.web.saviizoliacijaweb.repository;


import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface GyventojaiRepositoryInterface extends CrudRepository<Gyventojai, Long>
{
	List<Gyventojai> findByVardas(@Param("vardas") String vardas);
	List<Gyventojai> findByTelNr(@Param("telNr") String telNr);
	Gyventojai findOneByVardas(String vardas);
}
