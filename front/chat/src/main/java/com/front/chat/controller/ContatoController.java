package com.front.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.front.chat.model.Contato;
import com.front.chat.service.ContatoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/contato")
public class ContatoController {
	@Autowired
	private ContatoService contatoService;


	@GetMapping
	public ResponseEntity<List<Contato>> listar() {
		RestTemplate restTemplate = new RestTemplate();
		Contato[] contato = restTemplate.getForObject("http://localhost:8080/contato/contatos", Contato[].class);
		List<Contato> lista = new ArrayList<Contato>();
		for (Contato c : contato) {
			lista.add(c);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}

	@GetMapping("/{id}")
	public void listar1(Long id) {
		contatoService.getContato(id);
	}

	@GetMapping("/novo") 
	public String novoContato(Contato contato) {
		return "novoContato";
	}

	@GetMapping("/listar")
	public String listarContatos(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Contato[] contato = restTemplate.getForObject("http://localhost:8080/contato/contatos", Contato[].class);
		List<Contato> contatos = new ArrayList<Contato>();
		for (Contato c : contato) {
			contatos.add(c);
		}

		model.addAttribute("contatos", contatos);
		return "listaContato";
	}
	
	@GetMapping("/novo")
	public String alterarContato(Contato contato) {
		return "alterarContato";
	}
	


}