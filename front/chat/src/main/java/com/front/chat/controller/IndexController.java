package com.front.chat.controller;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import com.front.chat.service.ContatoService;
import com.front.chat.model.Contato;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @Autowired
    private ContatoService contatoService;

        
        @RequestMapping(value = "/index/{id}")
        public String index(Model model, @PathVariable Long id) {
            Contato contato = contatoService.getContato(id);
            model.addAttribute("contato", contato);
            return "index";
        }
    
}
