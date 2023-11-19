package com.front.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.front.chat.dto.MensagemDTO;
import com.front.chat.model.Contato;
import com.front.chat.model.Mensagem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MensagemService {
    private final String backendMensagemURI= "http://localhost:8080/mensagem";
    private final RestTemplate restTemplate = new RestTemplateBuilder().rootUri(backendMensagemURI).build();

    @Autowired
    private ContatoService contatoService;

    public Mensagem getMensagem(Long mensagemId) {
        Mensagem mensagem = restTemplate.getForObject( "/"+ mensagemId, Mensagem.class);
        return mensagem;
    }

    public List<Mensagem> getMensagens(Long userId) {
        String resource = "/listarRecebidas/"+userId;
        MensagemDTO[] response = restTemplate.getForObject(resource, MensagemDTO[].class);
        Mensagem[] mensagens = new Mensagem[response.length];
        for(int i = 0; i < response.length; i++) {
            mensagens[i] = new Mensagem(response[i]);
        }
        return new ArrayList<Mensagem>(Arrays.asList(mensagens));
    }

    public void deleteMensagem(Long mensagemId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Contato> requestBody = new HttpEntity<>(headers);
        String resource = "/deletar/" + mensagemId;

        restTemplate.exchange(resource, HttpMethod.DELETE, requestBody, Object.class);
    }

    public void  enviarMensagem(Mensagem mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Mensagem> requestBody = new HttpEntity<Mensagem>(mensagem, headers);
        log.info("Mensagem enviada: " + mensagem.toString());
        restTemplate.postForEntity("/enviar", requestBody, Object.class);
    }
}
