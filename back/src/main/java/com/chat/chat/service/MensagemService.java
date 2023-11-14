package com.chat.chat.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.chat.model.Contato;
import com.chat.chat.model.Mensagem;
import com.chat.chat.repositories.MensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	MensagemRepository mensagemRepository;
	
	public List<Mensagem> getMensagens(Contato receptor) {
		return mensagemRepository.findAllByReceptor(receptor);
	}
	
	public Mensagem getMensagem(Long id) {
		Optional<Mensagem> optionalMensagem = mensagemRepository.findById(id);
		
		if(optionalMensagem.isPresent())
			return optionalMensagem.get();
		
		return new Mensagem();
	}
	
	public Mensagem setMensagem(Mensagem mensagem) {
		return mensagemRepository.save(mensagem);
	}
	
	public Mensagem setMensagem(Long id, Mensagem mensagem) {
		Optional<Mensagem> optionalMensagem = mensagemRepository.findById(id);
		
		if(optionalMensagem.isPresent()) {
			mensagem.setId(id);
			return mensagemRepository.save(mensagem);
		}
		
		return new Mensagem();
	}
	
	public void deleteMensagem(Long id) {
		Optional<Mensagem> optionalMensagem = mensagemRepository.findById(id);
		
		if(optionalMensagem.isPresent())
			mensagemRepository.deleteById(id);
	}
}
