package com.contatamento.contatos_api.Controllers.Dtos;

import com.contatamento.contatos_api.Models.Contato;


public class CreateContato {

    public String nome;
    public Integer numero;

    public CreateContato() {}

    public Contato toContato(){

        return new Contato(this.nome, this.numero);
    }
}
