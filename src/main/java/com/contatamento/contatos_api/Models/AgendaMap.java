package com.contatamento.contatos_api.Models;

import java.util.*;

public class AgendaMap {
    private Map<String, Contato> listaContato = new HashMap<>();

    public Contato getContato(String telefone) {
        return listaContato.get(telefone);
    }

    public boolean adicionaContato(Contato contato) {
        return false;
    }

    public boolean removeContato(String telefone) {
        return false;
    }

    public Collection<Contato> getListaAgenda() {
        return null;
    }

    public Collection<Contato>  listaContato()
    {
        return null;
    }

    @Override
    public String toString() {
        return listaContato.toString();
    }
}
