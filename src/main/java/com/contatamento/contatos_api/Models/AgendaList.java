package com.contatamento.contatos_api.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AgendaList<T> {
    private List<T> listaContato = new ArrayList<>();


    private int localizaContato(String telefone)
    {
        return 0;
    }

    public Contato getContato(String telefone)
    {
        return null;
    }

    public boolean adicionaContato(Contato contato)
    {
        return false;
    }

    public boolean removeContato(String telefone)
    {
        return false;
    }

    public Collection<Contato> getListaAgenda()
    {
        return null;
    }

    @Override
    public String toString() {
        return listaContato.toString();
    }
}
