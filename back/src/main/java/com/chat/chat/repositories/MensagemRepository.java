package com.chat.chat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.chat.chat.model.Contato;
import  com.chat.chat.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	List<Mensagem> findByReceptor(Contato receptor);
	List<Mensagem> findByEmissor(Contato emissor);
}

