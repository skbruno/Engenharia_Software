package com.contatamento.contatos_api.Interfaces;

import com.contatamento.contatos_api.Controllers.Dtos.ContatoResponse;
import com.contatamento.contatos_api.Models.Contato;

import java.util.List;

public interface IContatoService {
    void CriarContato(Contato contato);
    List<Contato> ListarContato();
    ContatoResponse BuscarContatoId(Long id);
    void DeletarContato(Long id);
    void AlterarContato(Contato contato, Long id);
    void DeletarPrefixo(String prefixo);
}
