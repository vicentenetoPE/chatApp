package com.chat.chat.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chat.chat.model.Contato;
import com.chat.chat.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ContatoService {
    List<Contato> contatos = new ArrayList<>();

    @Autowired
	ContatoRepository contatoRepository;


	public List<Contato> getContatos(){
		return contatoRepository.findAll();
	}
	
	public Contato getContato(Long id) {
		Optional<Contato> optionalContato = contatoRepository.findById(id);
		if(optionalContato.isPresent())
			return optionalContato.get();
		return new Contato();
	}
	
	public Contato setContato(Contato contato) {
		return contatoRepository.save(contato);
	}
	
	public Contato setContato(Long id, Contato contato) {
		Optional<Contato> optionalContato = contatoRepository.findById(id);
		if(optionalContato.isPresent()) {
			contato.setId(id);
			return contatoRepository.save(contato);
		}
		return new Contato();
	}
	
	public void deleteContato(Long id) {
		Optional<Contato> optionalContato = contatoRepository.findById(id);
		if(optionalContato.isPresent())
			contatoRepository.deleteById(id);
	}
}
