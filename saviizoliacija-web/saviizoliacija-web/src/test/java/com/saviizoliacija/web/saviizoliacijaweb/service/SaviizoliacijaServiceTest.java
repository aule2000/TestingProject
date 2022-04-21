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

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.repository.SaviizoliacijaRepositoryInterface;
@ExtendWith(MockitoExtension.class)
class SaviizoliacijaServiceTest {

	@Mock
	SaviizoliacijaRepositoryInterface saviizoliacijaRepository;
	
	@InjectMocks
	SaviizoliacijaService service;
	
	@DisplayName("Test Find All")
	@Test
	void testFindAll() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		List<Saviizoliacija> saviizoliacijosList = new ArrayList<>();
		saviizoliacijosList.add(sav);
		
		when(saviizoliacijaRepository.findAll()).thenReturn(saviizoliacijosList);
		List<Saviizoliacija> found =  service.findAll();
		verify(saviizoliacijaRepository).findAll();
		assertEquals(1, found.size());
		
	}
	
	@Test
	void testFindById() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		when(saviizoliacijaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sav));
		
		Saviizoliacija found = service.findById(1);
		verify(saviizoliacijaRepository).findById(Mockito.anyLong());
		assertNotNull(found);
	}
	
	@Test
	void testUpdate() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		service.update(sav);
		verify(saviizoliacijaRepository).save(Mockito.any(Saviizoliacija.class));
	}
	
	@Test
	void testAdd() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		when(saviizoliacijaRepository.save(Mockito.any(Saviizoliacija.class))).thenReturn(sav);
		
		Saviizoliacija added = service.add(sav);
		verify(saviizoliacijaRepository).save(Mockito.any(Saviizoliacija.class));
		assertNotNull(added);
	}
	
	@Test
	void testDeleteById() {
		service.deleteById(1);
		verify(saviizoliacijaRepository).deleteById(Mockito.anyLong());
	}
	
	@Test
	void testDelete() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		service.delete(sav);
		verify(saviizoliacijaRepository).delete(Mockito.any(Saviizoliacija.class));
	}
	
	@Test
	void testFindBySaviizoliacijaByData() {
		Saviizoliacija sav = new Saviizoliacija(0,"",0);
		List<Saviizoliacija> saviizoliacijosList = new ArrayList<>();
		saviizoliacijosList.add(sav);
		when(saviizoliacijaRepository.findByData("01.05")).thenReturn(saviizoliacijosList);
		List<Saviizoliacija> found =  service.getSaviizoliacijaByData("01.05");
		verify(saviizoliacijaRepository).findByData("01.05");
		assertEquals(1, found.size());
		
	}
}
