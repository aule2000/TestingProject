package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
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

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.service.SaviizoliacijaService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SaviizoliacijaController.class)
class SaviizoliacijaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	SaviizoliacijaService service;
	
	@InjectMocks
	SaviizoliacijaController saviizoliacijaController;
	@Test
	void testShowAll() throws Exception{
		List<Saviizoliacija> sav = new ArrayList<Saviizoliacija>();
		sav.add(new Saviizoliacija(1,"2021-05-14", 1));
		sav.add(new Saviizoliacija(2, "2021-06-14", 0));
		when(service.findAll()).thenReturn(sav);
		
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/list-saviizoliacija")
				.accept(MediaType.TEXT_HTML);
		
		MvcResult res = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("list-saviizoliacija"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-saviizoliacija.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("saviizoliacija"))
				.andReturn();
	}
	

	@Test
	public void testShowAddPage() throws Exception{
		RequestBuilder rb = MockMvcRequestBuilders.get("/add-saviizoliacija");
		
		MvcResult rs = mockMvc.perform(rb)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("saviizoliacija"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/saviizoliacija.jsp"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("saviizoliacija"))
				.andExpect(MockMvcResultMatchers.model().attribute("saviizoliacija", hasProperty("id",notNullValue())))
				.andExpect(MockMvcResultMatchers.model().attribute("saviizoliacija", hasProperty("gyventojai",emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("saviizoliacija", hasProperty("data",emptyOrNullString())))
				.andExpect(MockMvcResultMatchers.model().attribute("saviizoliacija", hasProperty("saviizoliacijaON",notNullValue())))
				.andReturn();
	}
	
	@Test
	public void testAdd() throws Exception{
		when(service.add(Mockito.any(Saviizoliacija.class))).thenReturn(new Saviizoliacija("2021-05-14",0));
		
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/add-saviizoliacija")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param("data","2021-05-14")
				.param("saviizoliacijaON_OFF",String.valueOf(0))
				.flashAttr("saviizoliacija",new Saviizoliacija());
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-saviizoliacija"));
		
		verify(service).add(Mockito.any(Saviizoliacija.class));
	}
	
	@Test
	public void testUpdate() throws Exception
	{
		when(service.add(Mockito.any(Saviizoliacija.class))).thenReturn(new Saviizoliacija(1,"2021-04-14",1));
		RequestBuilder rb = MockMvcRequestBuilders
				.post("/update-saviizoliacija/"+1)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("data","2021-04-14")
				.param("saviizoliacijaON_OFF",String.valueOf(1))
				.flashAttr("saviizoliacija",new Saviizoliacija());
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-saviizoliacija"));
		
		verify(service).update(Mockito.any(Saviizoliacija.class));
	}
	
	@Test 
	public void testDelete() throws Exception{
		RequestBuilder rb = MockMvcRequestBuilders
				.get("/delete-saviizoliacija/{id}",Mockito.anyLong())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(1));
		
		mockMvc.perform(rb)
		.andExpect(MockMvcResultMatchers.status().isFound())
		.andExpect(MockMvcResultMatchers.view().name("redirect:/list-saviizoliacija"));
		
	}

}
