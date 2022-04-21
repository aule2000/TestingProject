package com.saviizoliacija.web.saviizoliacijaweb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.repository.GyventojaiRepositoryInterface;
@ExtendWith(MockitoExtension.class)
class GyventojaiServiceTest {

	@Mock
	GyventojaiRepositoryInterface gyventojaiRepository;
	
	@InjectMocks
	GyventojaiService service;
	
	@DisplayName("Test Find All")
	@Test
	void testFindAll() {
		Gyventojai gyv = new Gyventojai(0,"","");
		List<Gyventojai> gyventojai = new ArrayList<>();
		gyventojai.add(gyv);
		
		when(gyventojaiRepository.findAll()).thenReturn(gyventojai);
		List<Gyventojai> found =  service.findAll();
		verify(gyventojaiRepository).findAll();
		assertEquals(1, found.size());
		
	}
	
	@Test
	void testFindById() {
		Gyventojai gyv = new Gyventojai(0,"","");
		when(gyventojaiRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(gyv));
		
		Gyventojai found = service.findById(1);
		verify(gyventojaiRepository).findById(Mockito.anyLong());
		assertNotNull(found);
	}
	
	@Test
	void testUpdate() {
		Gyventojai gyv = new Gyventojai(0,"","");
		service.update(gyv);
		verify(gyventojaiRepository).save(Mockito.any(Gyventojai.class));
	}
	
	@Test
	void testAdd() {
		Gyventojai gyv = new Gyventojai(0,"","");
		when(gyventojaiRepository.save(Mockito.any(Gyventojai.class))).thenReturn(gyv);
		
		Gyventojai added = service.add(gyv);
		verify(gyventojaiRepository).save(Mockito.any(Gyventojai.class));
		assertNotNull(added);
	}
	
	@Test
	void testDeleteById() {
		service.deleteById(1);
		verify(gyventojaiRepository).deleteById(Mockito.anyLong());
	}
	
	@Test
	void testDelete() {
		Gyventojai gyv = new Gyventojai(0,"","");
		service.delete(gyv);
		verify(gyventojaiRepository).delete(Mockito.any(Gyventojai.class));
	}
	@Test
	void testFindByVardas() {
		Gyventojai gyv = new Gyventojai(0,"","");
		List<Gyventojai> gyventojai = new ArrayList<>();
		gyventojai.add(gyv);
		when(gyventojaiRepository.findByVardas("Aaa")).thenReturn(gyventojai);
		List<Gyventojai> found =  service.getGyventojasByVardas("Aaa");
		verify(gyventojaiRepository).findByVardas("Aaa");
		assertEquals(1, found.size());
		
	}
	
	

	
}
