package com.front.chat.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.front.chat.dto.MensagemDTO;

import lombok.Data;

@Data
public class Mensagem {
    private long id;
    private long emissor;
    private long receptor;
    private String conteudo;
    private String assunto;
    private String dataHora;

    public String formatDateHour(String dataHora) {
        OffsetDateTime odt = OffsetDateTime.parse(dataHora);
        LocalDateTime localDateTime = odt.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    public Mensagem(MensagemDTO mensagemDTO) {
        this.emissor = mensagemDTO.getEmissor().getId();
        this.receptor = mensagemDTO.getReceptor().getId();
        this.conteudo = mensagemDTO.getConteudo();
        this.assunto = mensagemDTO.getAssunto();
        this.dataHora = formatDateHour(mensagemDTO.getDataHora());
        this.id = mensagemDTO.getId();
    }

    public Mensagem() {
    }

 }
