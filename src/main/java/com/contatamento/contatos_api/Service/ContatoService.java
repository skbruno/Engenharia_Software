package com.contatamento.contatos_api.Service;

import com.contatamento.contatos_api.Controllers.Dtos.ContatoResponse;
import com.contatamento.contatos_api.Interfaces.IContatoRepository;
import com.contatamento.contatos_api.Interfaces.IContatoService;
import com.contatamento.contatos_api.Models.Contato;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContatoService implements IContatoService {

    private IContatoRepository _repository;

    public ContatoService (IContatoRepository repository){
        this._repository = repository;
    }

    public void CriarNovoContato(Contato contato){
        try {
            _repository.save(contato);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro interno", e);
        }
    }

    public List<Contato> PegarListaDeContato(){
        try {
            List<Contato> contatos = (List<Contato>) _repository.findAll();


            return contatos;
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro interno", e);
        }
    }

    public ContatoResponse BuscarContatoPorId(Long id){
        try {
            Contato contato = _repository.findById(id).orElse(null);

            if (contato == null){
                throw new Exception("Não foi possivel encontrar o id");
            }

            ContatoResponse contatoResponse = new ContatoResponse();
            contatoResponse.nome = contato.GetNome();
            contatoResponse.numero = contato.GetTelefone();
            contatoResponse.id = contato.GetId();

            return contatoResponse;
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro interno", e);
        }
    }

    public void DeletarContato(Long id){
        try {
            _repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro interno", e);
        }
    }

    public void AlterarContato(Contato contato, Long id)
    {
        try
        {
            Contato usuario = _repository.findById(id).orElse(null);
            if (usuario == null){
                throw new Exception("Não foi possivel encontrar o id");
            }

            usuario.setNome(contato.GetNome());
            usuario.setTelefone(contato.GetTelefone());
            _repository.save(usuario);

        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro interno", e);
        }
    }

    @Override
    public void DeletarContatosPorPrefixo(String prefixo)
    {
        try {
            List<Contato> contatos = (List<Contato>) _repository.findAll();

            if (contatos == null || contatos.isEmpty()){
                throw new Exception("Não foi possivel encontrar o id");
            }

            contatos.stream()
                    .filter(c -> c.GetNome().toLowerCase().startsWith(prefixo.toLowerCase()))
                    .forEach(_repository::delete);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
