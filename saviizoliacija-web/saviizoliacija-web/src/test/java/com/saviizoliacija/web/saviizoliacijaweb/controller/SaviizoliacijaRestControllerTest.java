package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.service.SaviizoliacijaService;

@WebMvcTest(value = SaviizoliacijaRestController.class)
class SaviizoliacijaRestControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    SaviizoliacijaService saviizoliacijaService;

    @AfterEach
	void tearDown() {
		reset(saviizoliacijaService); 
	}
    @Test
    void testShowAllSaviizoliacijosList() throws Exception
    {
        List<Saviizoliacija> saviizoliacijosList = new ArrayList<>();
        saviizoliacijosList.add(new Saviizoliacija(1, "2021-05-14",1));
        saviizoliacijosList.add(new Saviizoliacija(2, "2021-05-20" , 0));
        when(saviizoliacijaService.findAll()).thenReturn(saviizoliacijosList);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saviizoliacija")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"data\":\"2021-05-14\",\"saviizoliacijaON\":1},{\"id\":2,\"data\":\"2021-05-20\",\"saviizoliacijaON\":0}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    
    @Test
    void testSaviizoliacijosListById() throws Exception
    {
        Saviizoliacija saviizoliacija = new Saviizoliacija(1, "2021-05-14",1);
        when(saviizoliacijaService.findById(Mockito.anyLong())).thenReturn(saviizoliacija);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saviizoliacija/{id}", saviizoliacija.getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"data\":\"2021-05-14\",\"saviizoliacijaON\":1}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    
    @Test
    void tesAddSaviizoliacijosElement() throws Exception
    {
    	Saviizoliacija saviizoliacija = new Saviizoliacija(1, "2021-05-14",0);
        when(saviizoliacijaService.add(Mockito.any(Saviizoliacija.class))).thenReturn(saviizoliacija);

        String saviizoliacijaJson = "{\"id\":1,\"data\":\"2021-05-14\",\"saviizoliacijaON\":0}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/saviizoliacija")
                .accept(MediaType.APPLICATION_JSON)
                .content(saviizoliacijaJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/saviizoliacija/1", response.getHeader(HttpHeaders.LOCATION));
    }
    
    @Test
    public void testDeleteSaviizoliacijosElement() throws Exception
    {
    	Saviizoliacija saviizoliacija = new Saviizoliacija(1, "2021-05-14",0);
        when(saviizoliacijaService.add(Mockito.any(Saviizoliacija.class))).thenReturn(saviizoliacija);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/saviizoliacija/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    

}
