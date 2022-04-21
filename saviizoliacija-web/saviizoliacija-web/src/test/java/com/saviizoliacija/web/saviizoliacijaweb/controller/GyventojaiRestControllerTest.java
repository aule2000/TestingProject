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

import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;

@WebMvcTest(value = GyventojaiRestController.class)
class GyventojaiRestControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    GyventojaiService gyventojaiService;

    @AfterEach
	void tearDown() {
		reset(gyventojaiService);
	}
    
    @Test
    void testShowAllGyventojuList() throws Exception
    {
        List<Gyventojai> gyventojuList = new ArrayList<>();
        gyventojuList.add(new Gyventojai(1, "Jolanta", "+37062542837"));
        gyventojuList.add(new Gyventojai(2, "Juozas" , "+37062642837"));
        when(gyventojaiService.findAll()).thenReturn(gyventojuList);
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/gyventojai")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();
        String expected = "[{\"id\":1,\"vardas\":\"Jolanta\",\"telNr\":\"+37062542837\"},{\"id\":2,\"vardas\":\"Juozas\",\"telNr\":\"+37062642837\"}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    @Test
    void testGyventojasById() throws Exception
    {
        Gyventojai gyventojas = new Gyventojai(1, "Jolanta","+37062578693");
        when(gyventojaiService.findById(Mockito.anyLong())).thenReturn(gyventojas);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/gyventojai/{gyventojasId}", gyventojas.getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"vardas\":\"Jolanta\",\"telNr\":\"+37062578693\"}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
    
    @Test
    void testAddGyventoja() throws Exception
    {
        Gyventojai gyventojas = new Gyventojai(1, "Jolanta", "+37062472629");
        when(gyventojaiService.add(Mockito.any(Gyventojai.class))).thenReturn(gyventojas);

        String gyventojasJson = "{\"id\":1,\"vardas\":\"Jolanta\",\"telNr\":\"+37062472629\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(
                        "/gyventojai")
                .accept(MediaType.APPLICATION_JSON)
                .content(gyventojasJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/gyventojai/1", response.getHeader(HttpHeaders.LOCATION));
    }
    
    @Test
    public void testDeleteGyventoja() throws Exception
    {
        Gyventojai gyventojas = new Gyventojai(1, "Jolanta", "+37062456898");
        when(gyventojaiService.add(Mockito.any(Gyventojai.class))).thenReturn(gyventojas);
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/gyventojai/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
