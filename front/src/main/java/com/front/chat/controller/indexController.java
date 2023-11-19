package com.front.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.front.chat.service.ContatoService;
import com.front.chat.model.Contato;


@Controller
public class indexController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public String index(Contato contato,  Model model) {
        try {
			List<Contato> contatos = contatoService.getContatos();
			model.addAttribute("contatos", contatos);
		} catch (Exception e) {
		}
        return "index.html";
    }
    
}
