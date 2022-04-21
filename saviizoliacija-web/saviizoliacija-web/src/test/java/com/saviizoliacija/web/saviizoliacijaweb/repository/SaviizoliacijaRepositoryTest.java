package com.saviizoliacija.web.saviizoliacijaweb.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
@DataJpaTest
class SaviizoliacijaRepositoryTest {

	@Autowired
	protected SaviizoliacijaRepositoryInterface saviizoliacijaRepository;

	 @Test
	    public void testSave()	
	    {
	        Saviizoliacija saviizoliacija = new Saviizoliacija("2021-05-14",0);
	        saviizoliacijaRepository.save(saviizoliacija);
	        Saviizoliacija saviizoliacijaByData = saviizoliacijaRepository.findOneByData("2021-05-14");
	        assertNotNull(saviizoliacijaByData);
	        assertEquals(0, saviizoliacijaByData.getSaviizoliacijaON());
	    }
	 
	 	@Test
		public void testDelete() {
	 		Saviizoliacija o = new Saviizoliacija(1, "2021-05-15", 1);
	 		saviizoliacijaRepository.save(o);
	 		Saviizoliacija saviizoliacijaByData = saviizoliacijaRepository.findOneByData("2021-05-15");
			assertNotNull(saviizoliacijaByData);
			saviizoliacijaRepository.delete(saviizoliacijaByData);
			Iterable<Saviizoliacija> saviizoliacijosList = saviizoliacijaRepository.findAll();
			List<Saviizoliacija> result = StreamSupport.stream(saviizoliacijosList.spliterator(), false).collect(Collectors.toList());

			assertEquals(0, result.size());
		}
	 	
	 	@Test
		public void testFindByData() {
			Saviizoliacija o0 = new Saviizoliacija(1,"2021-04-13", 0);
			Saviizoliacija o1 = new Saviizoliacija(2,"2021-04-13", 1);
			Saviizoliacija o2 = new Saviizoliacija(3,"2021-04-15", 1);
			saviizoliacijaRepository.save(o0);
			saviizoliacijaRepository.save(o1);
			saviizoliacijaRepository.save(o2);

			Iterable<Saviizoliacija> saviizoliacijosElements = saviizoliacijaRepository.findByData("2021-04-13");

			assertNotNull(saviizoliacijosElements);
			List<Saviizoliacija> result = StreamSupport.stream(saviizoliacijosElements.spliterator(), false).collect(Collectors.toList());

			assertEquals(2, result.size());
		}
	 	
	 	@Test
		public void testFindBySaviizoliacijaON() {
			Saviizoliacija o0 = new Saviizoliacija(1,"2021-04-13", 0);
			Saviizoliacija o1 = new Saviizoliacija(2,"2021-04-13", 1);
			Saviizoliacija o2 = new Saviizoliacija(3,"2021-04-15", 1);
			saviizoliacijaRepository.save(o0);
			saviizoliacijaRepository.save(o1);
			saviizoliacijaRepository.save(o2);

			Iterable<Saviizoliacija> saviizoliacijosElements = saviizoliacijaRepository.findBySaviizoliacijaON(1);
			assertNotNull(saviizoliacijosElements);
			List<Saviizoliacija> result = StreamSupport.stream(saviizoliacijosElements.spliterator(), false).collect(Collectors.toList());

			assertEquals(2, result.size());
		}
	
	 	
	 	@Test
		public void testfindByDataAndSaviizoliacijaON() {
	 		Saviizoliacija o0 = new Saviizoliacija(1,"2021-04-13", 0);
			Saviizoliacija o1 = new Saviizoliacija(2,"2021-04-13", 1);
			Saviizoliacija o2 = new Saviizoliacija(3,"2021-04-15", 1);
			saviizoliacijaRepository.save(o0);
			saviizoliacijaRepository.save(o1);
			saviizoliacijaRepository.save(o2);

			Iterable<Saviizoliacija> saviizoliacijosElements = saviizoliacijaRepository.findByDataAndSaviizoliacijaON("2021-04-13", 0);
			assertNotNull(saviizoliacijosElements);
			List<Saviizoliacija> result = StreamSupport.stream(saviizoliacijosElements.spliterator(), false).collect(Collectors.toList());

			assertEquals(1, result.size());
		}
	 	
	 	@Test
		public void testFindAll() {
	 		Saviizoliacija o = new Saviizoliacija("2021-04-15", 1);
	 		saviizoliacijaRepository.save(o);

			Iterable<Saviizoliacija> saviizoliacijosElements = saviizoliacijaRepository.findAll();

			assertNotNull(saviizoliacijosElements);

			List<Saviizoliacija> result = new ArrayList<Saviizoliacija>();
			saviizoliacijosElements.forEach(result::add);
			assertEquals(1, result.size());
		}

	
	
}
