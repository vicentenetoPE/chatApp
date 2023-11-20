package com.front.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.front.chat.service.MensagemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.front.chat.model.Mensagem;
import org.springframework.web.bind.annotation.DeleteMapping;

@Log4j2
@Controller
@RequestMapping("/mensagem")
public class MensagemController {
    @Autowired MensagemService mensagemService;

    @GetMapping("/enviar")
    public String enviarMensagem(Mensagem mensagem){
        return "enviarMensagem";
    }

    @PostMapping("/enviar")
    public String postMethodName(Mensagem mensagem, BindingResult result, Model mode) {
        log.info("Mensagem enviada: " + mensagem.toString());
        if (result.hasErrors()) {
			return "redirect:/contato/novo";
		}
        mensagemService.postMensagem(mensagem);
        return "redirect:/contato";
    }

    @GetMapping("/listar/{idUsuario}")
    public String listarMensagens(Mensagem mensagem, @PathVariable Long idUsuario, Model model) {
        List<Mensagem> mensagens = mensagemService.getMensagens(idUsuario);
        model.addAttribute("mensagens", mensagens);
        return "listarMensagens";
    }

    @DeleteMapping("/deletar/{mensagemId}")
    public String deletarMensagem(@PathVariable Long mensagemId) {
        mensagemService.deleteMensagem(mensagemId);
        return "redirect:/contato";
    }

    
}
