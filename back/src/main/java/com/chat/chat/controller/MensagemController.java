package com.chat.chat.controller;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping("/mensagem")
public class MensagemController {
	@Autowired
	MensagemService mensagemService;
	
	@Autowired
	ContatoService contatoService;
	
	@GetMapping("/listar/{idUsuario}")
	ResponseEntity<List<Mensagem>> getMensagens(@PathVariable Long idUsuario) {
		Contato receptor = contatoService.getContato(idUsuario);
		List<Mensagem> mensagens = mensagemService.getMensagens(receptor);
		
		if(mensagens.isEmpty()) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		else 
			return ResponseEntity.ok(mensagens);
	}

	@GetMapping("/abrir/{idMensagem}")
	ResponseEntity<Object> getMensagem(@PathVariable Long idMensagem) {
		Mensagem mensagem = mensagemService.getMensagem(idMensagem);
		if(mensagem.getId() == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Vector<>());
		else 
			return ResponseEntity.ok(mensagem);
	}
	
	@PostMapping("/enviar")
	ResponseEntity<Mensagem> setMensagem(@RequestBody MensagemDTO mensagemDTO) {
		Contato emissor = contatoService.getContato(mensagemDTO.getEmissor());
		Contato receptor = contatoService.getContato(mensagemDTO.getReceptor());
		
		Mensagem mensagem = new Mensagem();
		mensagem.setDataHora(mensagemDTO.getDataHora());
		mensagem.setConteudo(mensagemDTO.getConteudo());
		mensagem.setEmissor(emissor);
		mensagem.setReceptor(receptor);
		
		mensagemService.setMensagem(mensagem);
		return ResponseEntity.created(null).body(mensagem);
	}
	
	
	@DeleteMapping("/deletar/{id}")
	ResponseEntity<Mensagem> deleteMensagem(@PathVariable Long id) {
		try {
			mensagemService.deleteMensagem(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Mensagem());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem());
		}
	}
}
