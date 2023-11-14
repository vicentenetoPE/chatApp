package com.front.chat.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.front.chat.model.Mensagem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MensagemService {
    private final String backendMensagemURI= "http://localhost:8080/mensagem/";
    private final RestTemplate restTemplate = new RestTemplateBuilder().rootUri(backendMensagemURI).build();


    public Mensagem getMensagem(Long mensagemId) {
        Mensagem mensagem = restTemplate.getForObject( "/"+ mensagemId, Mensagem.class);
        return mensagem;
    }

    public List<Mensagem> getMensagens(Long userId) {
        Mensagem[] mensagens = restTemplate.getForObject("/"+userId, Mensagem[].class);
        return new ArrayList<Mensagem>(Arrays.asList(mensagens));
    }

    public void deleteMensagem(Long mensagemId) {
        restTemplate.delete("/deletar"+mensagemId);
    }

    public boolean  sendMensagem(Mensagem mensagem) {
        ResponseEntity<Mensagem> response = restTemplate.postForEntity("/enviar", mensagem, Mensagem.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return true;
        } else {
            return false;
        }   
    }
}
