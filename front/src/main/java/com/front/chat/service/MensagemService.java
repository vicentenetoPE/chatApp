package com.front.chat.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.front.chat.dto.MensagemDTO;
import com.front.chat.model.Contato;
import com.front.chat.model.Mensagem;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MensagemService {
    private final String backendMensagemURI= "http://localhost:8080/mensagem";
    private final RestTemplate restTemplate = new RestTemplateBuilder().rootUri(backendMensagemURI).build();

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

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
        String resource = "/deletar/" + mensagemId;
        HttpEntity<Contato> requestBody = new HttpEntity<>(createHttpHeaders());
        restTemplate.exchange(resource, HttpMethod.DELETE, requestBody, Object.class);
    }

    public void  postMensagem(Mensagem mensagem) {
        HttpEntity<Mensagem> requestBody = new HttpEntity<Mensagem>(mensagem, createHttpHeaders());
        restTemplate.postForEntity("/enviar", requestBody, Object.class);
    }
}
