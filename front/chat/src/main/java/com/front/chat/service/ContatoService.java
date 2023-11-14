package com.front.chat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.front.chat.ChatApplication;
import com.front.chat.model.Contato;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ContatoService {

    private final String backendContatoURI= "http://localhost:8080/contato";
    private final RestTemplate restTemplate = new RestTemplateBuilder().rootUri(backendContatoURI).build();

    private static final Logger log = LoggerFactory.getLogger(ChatApplication.class);
    
    public Contato getContato(Long id) {
        String url = "/contato/"+id;
        Contato contato = restTemplate.getForObject(url, Contato.class);
        return contato;
    }

    public Contato postContato(Contato contato) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Contato> requestBody = new HttpEntity<Contato>(contato, headers);
		
		ResponseEntity<Contato> response = restTemplate.postForEntity("http://localhost:8080", requestBody, Contato.class);
		
		return response.getBody();
	}
    
}
