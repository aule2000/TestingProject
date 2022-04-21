package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.saviizoliacija.web.saviizoliacijaweb.SaviizoliacijaWebApplication;
import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.service.GyventojaiService;


@SpringBootTest(classes = SaviizoliacijaWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GyventojaiRestControllerTestIT {

	@LocalServerPort
	private int port;
	
	
	@Autowired
	GyventojaiService service;


	@Test
	void test() {
		System.out.println("PORT=" + port);
	}
	@Test
	void testFindGyventojaById() throws Exception{
		String expected = "{\"id\":1,\"vardas\":\"Aaa\",\"telNr\":\"370111\"}";
		WebTestClient
		.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build()
		.get()
		.uri("/gyventojai/1")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().valueEquals("Content-Type", "application/json")
		.expectBody().json(expected);
	
	}
	
	@Test
	void testAddGyventoja() {
		Gyventojai gyventojas = new Gyventojai(7,"Ggg","370777"); 
		WebTestClient
		.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build()
		.post()
		.uri("/gyventojai").bodyValue(gyventojas)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().location("http://localhost:"+port+ "/gyventojai/" + gyventojas.getId());
	}
		
	@Test
	void testDeleteGyventoja() {
		Gyventojai sample = new Gyventojai(6,"Fff","370666");
		WebTestClient.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build().delete()
		.uri("/gyventojai/"+sample.getId())
		.exchange()
		.expectStatus().isOk();
	}
	
	
	@Test
    public void testUpdate() throws IOException
    {
        Gyventojai sample = new Gyventojai(3,"Ccc","370333");
        service.update(sample);
        HttpUriRequest request = RequestBuilder.create("PUT")
                .setUri("http://localhost:" + port + "/gyventojai/3")
                .setEntity(new StringEntity("{\"vardas\":\"Ccc1\",\"telNr\":\"370331\"}", ContentType.APPLICATION_JSON))
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.OK.value()));
    }
	

	
	
}
