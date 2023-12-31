package com.front.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.front.chat.model.Contato;
import com.front.chat.service.ContatoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Log4j2
@Controller
@RequestMapping("/contato")
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping
	public String listAll(Model model) {
		try {
			List<Contato> contatos = contatoService.getContatos();
			model.addAttribute("contatos", contatos);
		} catch (Exception e) {
			log.error("Erro ao listar contatos", e);		}
		return "listaContato";
	}

	@GetMapping("/novo")
	public String newContact(Contato contato) {
		return "criaContato";
	}

	@GetMapping("/alterar/{contatoId}")
	public String editContact(@PathVariable Long contatoId, Model model) {
		model.addAttribute("contato", contatoService.getContato(contatoId));
		return "alterarContato";
	}


	@PostMapping
	public String inserirContato(Contato contato, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "novoContato";
		}
		contatoService.postContato(contato);
		return "redirect:/contato";
	}

	@PutMapping("/alterar/{id}")
	public String alterarContato(@PathVariable Long id, Contato contato, BindingResult result, Model model) {
		contatoService.putContato(contato, id);
		return "redirect:/contato";
	}

	@DeleteMapping("/{id}")
    public String excluirContato(@PathVariable long id) {
    	contatoService.deleteContato(id);
    	return "redirect:/contato";
    }

}