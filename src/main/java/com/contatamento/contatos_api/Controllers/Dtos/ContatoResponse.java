package com.contatamento.contatos_api.Controllers.Dtos;

public class ContatoResponse {
    public Long id;
    public String nome;
    public Long numero;


    public ContatoResponse(){}

    public ContatoResponse(Long id, String nome, Long numero)
    {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }


}


