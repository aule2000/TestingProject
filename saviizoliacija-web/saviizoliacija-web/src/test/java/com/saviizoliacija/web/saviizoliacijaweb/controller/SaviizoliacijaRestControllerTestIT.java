package com.saviizoliacija.web.saviizoliacijaweb.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.saviizoliacija.web.saviizoliacijaweb.SaviizoliacijaWebApplication;
import com.saviizoliacija.web.saviizoliacijaweb.model.Gyventojai;
import com.saviizoliacija.web.saviizoliacijaweb.model.Saviizoliacija;
import com.saviizoliacija.web.saviizoliacijaweb.service.SaviizoliacijaService;

@SpringBootTest(classes = SaviizoliacijaWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class SaviizoliacijaRestControllerTestIT {

	@LocalServerPort
	private int port;
	
	
	@Autowired
	SaviizoliacijaService service;

	private Saviizoliacija saviizoliacija;
	@BeforeEach
	void setUp() {
		saviizoliacija = service.findAll().get(0);
	}
	@Test
	void test() {
		System.out.println("PORT=" + port);
	}
	
	@Test
	void testFindSaviizoliacijosListById() throws Exception{
		String expected = "{\"id\":1,\"data\":\"01.05\",\"saviizoliacijaON\":1}";
		WebTestClient
		.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build()
		.get()
		.uri("/saviizoliacija/1")
		.exchange()
		.expectStatus().isOk()
		.expectHeader().valueEquals("Content-Type", "application/json")
		.expectBody().json(expected);
	}
	@Test
	void testAddSaviizoliacijosElement() {
		Gyventojai gyv = new Gyventojai(1,"Aaa","370111");
		Saviizoliacija sav = new Saviizoliacija(8,"03.15",0,gyv);
		WebTestClient
		.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build()
		.post()
		.uri("/saviizoliacija").bodyValue(sav)
		.exchange()
		.expectStatus().isCreated()
		.expectHeader().location("http://localhost:"+port+ "/saviizoliacija/"+sav.getId());
	}

	@Test
	void testDeleteSaviizoliacijosElement() {
		Saviizoliacija sample = new Saviizoliacija(6,"01.26",0);
		WebTestClient.bindToServer()
		.baseUrl("http://localhost:"+port)
		.build().delete()
		.uri("/saviizoliacija/"+sample.getId())
		.exchange()
		.expectStatus().isOk();
	}
	
	
	@Test
    public void testUpdate() throws IOException
    {
		Saviizoliacija sample = new Saviizoliacija(3,"01.24",1);
        service.update(sample);
        HttpUriRequest request = RequestBuilder.create("PUT")
                .setUri("http://localhost:" + port + "/saviizoliacija/3")
                .setEntity(new StringEntity("{ \"gyventojai\": {\"id\":3,\"vardas\":\"Ccc\", \"telNr\":\"370333\"},\"data\":\"03.28\",\"saviizoliacijaON\":0}", ContentType.APPLICATION_JSON))
                .build();
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.OK.value()));
    }
	

}
