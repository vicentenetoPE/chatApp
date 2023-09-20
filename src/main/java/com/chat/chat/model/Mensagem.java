package com.chat.chat.model;

import java.util.Calendar;
import java.util.Date;


import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.chat.chat.model.Contato;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mensagem {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String conteudo;
    private Calendar dataHora = Calendar.getInstance();
        
    @OneToOne
    @JoinColumn(name = "emissor_id")
    private Contato emissor;

    @OneToOne
    @JoinColumn(name = "receptor_id")
    private Contato receptor;
}
