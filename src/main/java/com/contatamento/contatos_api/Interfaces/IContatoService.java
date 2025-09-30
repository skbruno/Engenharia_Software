package com.contatamento.contatos_api.Interfaces;

import com.contatamento.contatos_api.Controllers.Dtos.ContatoResponse;
import com.contatamento.contatos_api.Models.Contato;

import java.util.List;

public interface IContatoService {
    void CriarNovoContato(Contato contato);
    List<Contato> PegarListaDeContato();
    ContatoResponse BuscarContatoPorId(Long id);
    void DeletarContato(Long id);
    void AlterarContato(Contato contato, Long id);
    void DeletarContatosPorPrefixo(String prefixo);
}
