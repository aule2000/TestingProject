package com.saviizoliacija.web.saviizoliacijaweb.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;

public class GyventojaiTest {
	
	@Test
	void testIdStringStringConstructor() {
		Gyventojai gyventojas = new Gyventojai(1L,"Valdas","+37062542836");
		assertAll("Test Gyventojai Consctructor",
				() -> assertEquals(1L, gyventojas.getId() ),
				() -> assertEquals("Valdas",gyventojas.getVardas()),
				() -> assertEquals("+37062542836",gyventojas.getTelNr())
				);
	}
	
	@Test 
	void testComapareTo(){
		Gyventojai gyventojas1 = new Gyventojai(1, "Aloyzas", "+37062542836");
		Gyventojai gyventojas2 = new Gyventojai(1, "Aloyzas", "+37062542836");
		assertEquals(0,gyventojas1.compareTo(gyventojas2));
	}
	
	@Test 
	void testEqualsObject() {
		Gyventojai gyventojas1 = new Gyventojai(1, "Aloyzas", "+37062542836");
		Gyventojai gyventojas2 = new Gyventojai(1, "Aloyzas", "+37062542836");
		assertTrue(gyventojas1.equals(gyventojas2));
	}
	
	@Test 
	void testSetId() {
		Gyventojai gyventojas = new Gyventojai();
		gyventojas.setId(10);
		long value = gyventojas.getId();
		assertEquals(value,10);
	}
	@Test
	void testSetVardas() {
		Gyventojai gyventojas = new Gyventojai();
		gyventojas.setVardas("Jonas");
		String value = gyventojas.getVardas();
		assertEquals(value,"Jonas");	
	}
	@Test
	void testSetPhoneNumber() {
		Gyventojai gyventojas = new Gyventojai();
		gyventojas.setTelNr("+37062542836");
		String value = gyventojas.getTelNr();
		assertEquals(value,"+37062542836");		
	}
	
}
