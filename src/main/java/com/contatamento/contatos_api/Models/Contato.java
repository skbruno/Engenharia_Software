package com.contatamento.contatos_api.Models;

import jakarta.persistence.*;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private Integer telefone;

    public Contato(){}

    public boolean CompararTelefone(Integer telefone){
        if(this.telefone == telefone){
            return true;
        }

        return false;
    }

    public Contato(String nome, Integer telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    public String GetNome(){
        return this.nome;
    }

    public Integer GetTelefone(){
        return this.telefone;
    }

    public Long GetId(){
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
}
