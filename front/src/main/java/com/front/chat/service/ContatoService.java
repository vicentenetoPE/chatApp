package com.front.chat.service;

import java.util.ArrayList;

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
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpMethod;

@Service
public class ContatoService {

    private final String backendContatoURI = "http://localhost:8080/contato";
    private final RestTemplate restTemplate = new RestTemplateBuilder().rootUri(backendContatoURI).build();

    public Contato getContato(Long id) {
        String resource = "/" + id;
        Contato contato = restTemplate.getForObject(resource, Contato.class);
        return contato;
    }

    public List<Contato> getContatos() {
        String resource = "/listar";
        ResponseEntity<Contato[]> response = restTemplate.getForEntity(resource, Contato[].class);
        return new ArrayList<Contato>(Arrays.asList(response.getBody()));
    }

    public Contato postContato(Contato contato) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Contato> requestBody = new HttpEntity<Contato>(contato, headers);

        ResponseEntity<Contato> response = restTemplate.postForEntity("/criar", requestBody, Contato.class);

        return response.getBody();
    }

    public Contato putContato(Contato contato, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Contato> requestBody = new HttpEntity<>(contato, headers);
        String resource = "/" + id;
        ResponseEntity<Contato> response = restTemplate.exchange(resource, HttpMethod.PUT, requestBody, Contato.class);
        return response.getBody();

    }

    public void deleteContato(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Contato> requestBody = new HttpEntity<>(headers);
        String resource = "/" + id;
        restTemplate.exchange(resource, HttpMethod.DELETE, requestBody, Contato.class);
    }
}
