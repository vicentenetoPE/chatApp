package com.front.chat.dto;

import com.front.chat.model.Contato;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MensagemDTO {
    
	private Long id;
    private String conteudo;
    private String assunto;
    private String dataHora;
    private Contato emissor;
    private Contato receptor;

    public MensagemDTO(Long id, String conteudo, String assunto, String dataHora, Contato emissor, Contato receptor) {    
        this.conteudo = conteudo;
        this.assunto = assunto;
        this.id = id;
    }

}


