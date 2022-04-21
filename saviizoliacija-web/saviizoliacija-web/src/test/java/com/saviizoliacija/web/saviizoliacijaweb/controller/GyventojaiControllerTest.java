package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GyventojaiController.class) 
class GyventojaiControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	GyventojaiService service;
	
	@InjectMocks
	GyventojaiController gyventojaiController;
	@Test
	void testShowAll() throws Exception{
		List<Gyventojai> gyv = new ArrayList<Gyventojai>();
		gyv.add(new Gyventojai(1, "Jolanta", "+37062542836"));
		gyv.add(new Gyventojai(2, "Juozas", "+37062542736"));
		when(service.findAll()).thenReturn(gyv);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/list-gyventojai")
				.accept(MediaType.TEXT_HTML);
		
		MvcResult res = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("list-gyventojai"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-gyventojai.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("gyventojai"))
				.andReturn();
	}
	

	@Test
	public void testShowAddPage() throws Exception{
		RequestBuilder rb = MockMvcRequestBuilders.get("/add-gyventoja");
		
		MvcResult rs = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("gyventojas"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/gyventojas.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("gyventojas"))
				.andExpect(MockMvcResultMatchers.model().attribute("gyventojas", hasProperty("id",notNullValue())))
				.andExpect(MockMvcResultMatchers.model().attribute("gyventojas", hasProperty("vardas",emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("gyventojas", hasProperty("telNr",notNullValue())))
				.andReturn();
	}
	
	@Test
	public void testAdd() throws Exception{
		when(service.add(Mockito.any(Gyventojai.class))).thenReturn(new Gyventojai("Jolanta","+37062458759"));
		
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/add-gyventoja")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("vardas","Jolanta")
				.param("telNr","+37062458759")
				.flashAttr("gyventojas",new Gyventojai());
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-gyventojai"));
		
		verify(service).add(Mockito.any(Gyventojai.class));
	}
	
	@Test
	public void testUpdate() throws Exception
	{
		when(service.add(Mockito.any(Gyventojai.class))).thenReturn(new Gyventojai(1,"Jolanta","+370625475836"));
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/update-gyventojas/"+1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("vardas","Jolanta")
				.param("telNr","+370625475836")
				.flashAttr("gyventojas",new Gyventojai());
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-gyventojai"));
		
		verify(service).update(Mockito.any(Gyventojai.class));
	}
	
	@Test 
	public void testDelete() throws Exception{
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/delete-gyventojas/{gyventojoId}",Mockito.anyLong())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("gyventojoId", String.valueOf(1));
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-gyventojai"));
		
	}

}
