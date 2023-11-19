package com.chat.chat.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


import com.chat.chat.dto.MensagemDTO;
import com.chat.chat.model.Contato;
import com.chat.chat.model.Mensagem;
import com.chat.chat.service.ContatoService;
import com.chat.chat.service.MensagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
@RestController
@RequestMapping("/mensagem")
public class MensagemController {

	private static final Logger logger = LogManager.getLogger(MensagemController.class);

	@Autowired
	MensagemService mensagemService;
	
	@Autowired
	ContatoService contatoService;
	
	@GetMapping("/listarRecebidas/{idUsuario}")
	ResponseEntity<List<Mensagem>> getMensagensRecebidas(@PathVariable Long idUsuario) {
		List<Mensagem> mensagens = mensagemService.getMensagensReceptor(idUsuario);
		
		if(mensagens.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		else 
			return ResponseEntity.ok(mensagens);
	}

	@GetMapping("/listarEnviadas/{idUsuario}")
	ResponseEntity<List<Mensagem>> getMensagensEnviadas(@PathVariable Long idUsuario) {
		List<Mensagem> mensagens = mensagemService.getMensagensEmissor(idUsuario);
		
		if(mensagens.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		else 
			return ResponseEntity.ok(mensagens);
	}
	
	@PostMapping("/enviar")
	ResponseEntity<Mensagem> setContato(@RequestBody MensagemDTO dtoMensagem) {
		log.info("Mensagem recebida: " + dtoMensagem.toString());
		return ResponseEntity.created(null).body(mensagemService.setMensagemFromDTOMensagem(dtoMensagem));
	}

	@GetMapping("/listar")
	ResponseEntity<List<Mensagem>> getMensagens() {
		List<Mensagem> mensagens = mensagemService.getMensagens();
		
		if(mensagens.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		else 
			return ResponseEntity.ok(mensagens);
	}
	
	
	@DeleteMapping("/deletar/{id}")
	ResponseEntity<Mensagem> deleteMensagem(@PathVariable Long id) {
		try {
			mensagemService.deleteMensagem(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem());
		}
	}
}
