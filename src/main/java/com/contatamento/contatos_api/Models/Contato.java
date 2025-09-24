package com.contatamento.contatos_api.Models;

import jakarta.persistence.*;

@Entity
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private Long telefone;

    public Contato(){}

    public boolean CompararTelefone(Long telefone){
        if(this.telefone == telefone){
            return true;
        }

        return false;
    }

    public Contato(String nome, Long telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    public String GetNome(){
        return this.nome;
    }

    public Long GetTelefone(){
        return this.telefone;
    }

    public Long GetId(){
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
