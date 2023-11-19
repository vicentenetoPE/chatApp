package com.front.chat.dto;

import com.front.chat.model.Contato;

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

    public MensagemDTO() {    
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {    
        this.conteudo = conteudo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {    
        this.assunto = assunto;
    }

    public Contato getEmissor() {
        return emissor;
    }

    public void setEmissor(Contato emissor) {    
        this.emissor = emissor;
    }

    public Contato getReceptor() {
        return receptor;
    }

    public void setReceptor(Contato receptor) {    
        this.receptor = receptor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {    
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {    
        this.id = id;
    }


}


