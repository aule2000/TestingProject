package com.saviizoliacija.web.saviizoliacijaweb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;



@DataJpaTest
class GyventojaiRepositoryTest {

		@Autowired
		protected GyventojaiRepositoryInterface gyventojaiRepository;
	 	
		@Test
	    public void testSave()
	    {
	        Gyventojai gyventojas = new Gyventojai(1, "Jolanta", "+37062542736");
	        gyventojaiRepository.save(gyventojas);
	        Gyventojai gyventojasByVardas = gyventojaiRepository.findOneByVardas("Jolanta");
	        assertNotNull(gyventojasByVardas);
	        assertEquals(1, gyventojasByVardas.getId());
	    }
	 
	 	@Test
		public void testDelete() {
			Gyventojai o = new Gyventojai(1, "Jolanta", "+37062572836");
			gyventojaiRepository.save(o);
			Gyventojai gyventojasByVardas = gyventojaiRepository.findOneByVardas("Jolanta");
			assertNotNull(gyventojasByVardas);
			gyventojaiRepository.delete(gyventojasByVardas);
			Iterable<Gyventojai> gyventojai = gyventojaiRepository.findAll();

			List<Gyventojai> result = StreamSupport.stream(gyventojai.spliterator(), false).collect(Collectors.toList());
			assertEquals(0, result.size());
		}
	 	
	 	@Test
		public void testfindByVardas() {
			Gyventojai o0 = new Gyventojai(1, "Jolanta", "+37062578963");
			Gyventojai o1 = new Gyventojai(2, "Juozas", "+37062578963");
			Gyventojai o2 = new Gyventojai(3, "Kotryna", "+37062578963");
			gyventojaiRepository.save(o0);
			gyventojaiRepository.save(o1);
			gyventojaiRepository.save(o2);

			Iterable<Gyventojai> gyventojai = gyventojaiRepository.findByVardas("Juozas");

			assertNotNull(gyventojai);
			List<Gyventojai> result = StreamSupport.stream(gyventojai.spliterator(), false).collect(Collectors.toList());

			assertEquals(1, result.size());
		}
	 	
	 	@Test
		public void testfindByTelNr() {
			Gyventojai o0 = new Gyventojai(1, "Jolanta", "+37062578963");
			Gyventojai o1 = new Gyventojai(2, "Juozas", "+37062578963");
			Gyventojai o2 = new Gyventojai(3, "Kotryna", "+37062578963");
			gyventojaiRepository.save(o0);
			gyventojaiRepository.save(o1);
			gyventojaiRepository.save(o2);

			Iterable<Gyventojai> gyventojai = gyventojaiRepository.findByTelNr("+37062578963");

			assertNotNull(gyventojai);

			List<Gyventojai> result = StreamSupport.stream(gyventojai.spliterator(), false).collect(Collectors.toList());

			assertEquals(3, result.size());
		}
	 	
	 	@Test
	    public void testFindAll()
	    {
	        Gyventojai gyventojas = new Gyventojai(1, "Juozas", "+37062542739");
	        gyventojaiRepository.save(gyventojas);
	        Iterable<Gyventojai> gyventojai = gyventojaiRepository.findAll();
	        assertNotNull(gyventojai);
	        List<Gyventojai> result = new ArrayList<>();
	        gyventojai.forEach(result::add);
	        assertEquals(1, result.size());
	    }
	 

}
