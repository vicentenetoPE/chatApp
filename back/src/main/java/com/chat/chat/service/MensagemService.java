package com.chat.chat.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chat.chat.model.Mensagem;
import com.chat.chat.repositories.MensagemRepository;
import com.chat.chat.dto.MensagemDTO;

@Service
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private ContatoService contatoService;
	
	public List<Mensagem> getMensagens(){
		return mensagemRepository.findAll();
	}
	public List<Mensagem> getMensagensReceptor(Long idReceptor){
		return mensagemRepository.findByReceptor(contatoService.getContato(idReceptor));
	}
	
	public List<Mensagem> getMensagensEmissor(Long idEmissor){
		return mensagemRepository.findByEmissor(contatoService.getContato(idEmissor));
	}
	
	public Mensagem setMensagemFromDTOMensagem(MensagemDTO dtoMensagem) {
		Mensagem mensagem = new Mensagem();
		mensagem.setConteudo(dtoMensagem.getConteudo());
		mensagem.setAssunto(dtoMensagem.getAssunto());
		mensagem.setEmissor(contatoService.getContato(dtoMensagem.getEmissor()));
		mensagem.setReceptor(contatoService.getContato(dtoMensagem.getReceptor()));
		return mensagemRepository.save(mensagem);

	}

	public void deleteMensagem(Long idMensagem) {
		mensagemRepository.deleteById(idMensagem);
	}
	
}
