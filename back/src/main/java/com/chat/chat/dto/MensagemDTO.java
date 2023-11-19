package com.chat.chat.dto;

public class MensagemDTO {
	private String conteudo;
	private Long emissor;
	private Long receptor;
	private String assunto;
	
	public MensagemDTO(String conteudo, String assunto, Long emissor, Long receptor) {
		super();
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.emissor = emissor;
		this.receptor = receptor;
	}
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Long getEmissor() {
		return emissor;
	}
	public void setEmissor(Long emissor) {
		this.emissor = emissor;
	}
	public Long getReceptor() {
		return receptor;
	}
	public void setReceptor(Long receptor) {
		this.receptor = receptor;
	}
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}	
}
