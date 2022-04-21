package com.saviizoliacija.web.saviizoliacijaweb.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;

class SaviizoliacijaTest {

	@Test
	void testSaviizoliacijaLongStringIntConstructor() {
		Saviizoliacija saviizoliacija = new Saviizoliacija(1,"2021-05-14",0);
		assertAll("Test Saviizoliacijos constructor",
				() -> assertEquals(1, saviizoliacija.getId()),
				() -> assertEquals("2021-05-14",saviizoliacija.getData()),
				() -> assertEquals(0, saviizoliacija.getSaviizoliacijaON())

				);
	}
	@Test
	void testCompareTo() {
		Saviizoliacija saviizoliacija1 = new Saviizoliacija(1,"2021-11-12",0);
		Saviizoliacija saviizoliacija2 = new Saviizoliacija(1,"2021-11-12",0);
		assertEquals(0, saviizoliacija1.compareTo(saviizoliacija2));
	}
	
	@Test
	void testEqualsObject() {
		Saviizoliacija saviizoliacija1 = new Saviizoliacija(1,"2021-11-12",0);
		Saviizoliacija saviizoliacija2 = new Saviizoliacija(1,"2021-11-12",0);
		assertTrue(saviizoliacija1.equals(saviizoliacija2));
	}
	
	@Test 
	void testSetId() {
		Saviizoliacija saviizoliacija = new Saviizoliacija();
		saviizoliacija.setId(10);
		long value = saviizoliacija.getId();
		assertEquals(value,10);
	}
	@Test
	void testSetDate() {
		Saviizoliacija saviizoliacija = new Saviizoliacija();
		saviizoliacija.setData("01.16");
		String value = saviizoliacija.getData();
		assertEquals(value,"01.16");
		
	}
	@Test 
	void testSetSaviizoliacijaOnOrOff() {
		Saviizoliacija saviizoliacija = new Saviizoliacija();
		saviizoliacija.setSaviizoliacijaON(1);
		int value = saviizoliacija.getSaviizoliacijaON();
		assertEquals(value,1);
	}

}
