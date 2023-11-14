package com.chat.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.chat.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
