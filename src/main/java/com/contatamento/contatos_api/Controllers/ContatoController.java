package com.contatamento.contatos_api.Controllers;

import com.contatamento.contatos_api.Controllers.Dtos.ContatoResponse;
import com.contatamento.contatos_api.Controllers.Dtos.CreateContato;
import com.contatamento.contatos_api.Controllers.Dtos.ResponseMessage;
import com.contatamento.contatos_api.Interfaces.IContatoService;
import com.contatamento.contatos_api.Models.Contato;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/contatos")
public class ContatoController {

    private final IContatoService _service;

    public ContatoController(IContatoService service) {
        this._service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContatoResponse>> listar() {
        List<ContatoResponse> listaDeContatos = new ArrayList<>();
        List<Contato> contatos = _service.PegarListaDeContato();

        if (contatos.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        for (Contato contato : contatos) {
            ContatoResponse dto = new ContatoResponse();
            dto.nome = contato.GetNome();
            dto.numero = contato.GetTelefone();
            dto.id = contato.GetId();
            listaDeContatos.add(dto);
        }

        return ResponseEntity.ok(listaDeContatos);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> criar(@RequestBody CreateContato dto) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (dto.nome.isEmpty()){
            responseMessage.messagem = "O campo nome não pode esta em branco.";
            return ResponseEntity.badRequest().body(responseMessage);
        }

        List<Contato> contatos = _service.PegarListaDeContato();

        for (Contato contatoItem : contatos) {
            if (contatoItem.CompararTelefone(dto.numero)) {
                responseMessage.messagem = "Número inserido já existe";
                return ResponseEntity.badRequest().body(responseMessage);
            }
        }

        Contato contato = new Contato();
        contato.setNome(dto.nome);
        contato.setTelefone(dto.numero);

        _service.CriarNovoContato(contato);
        URI location = URI.create("/contatos/");

        responseMessage.messagem = "Contato criado com sucesso.";
        return ResponseEntity.created(location).body(responseMessage);
    }


    @GetMapping("/by-id")
    public ResponseEntity buscarPorId(@RequestParam Long id) {
        ContatoResponse contatoResponse1 = _service.BuscarContatoPorId(id);

        if (contatoResponse1 == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Contato não encontrado"));
        }

        return ResponseEntity.ok(contatoResponse1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> responseMessage(@PathVariable Long id) {
        _service.DeletarContato(id);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.messagem = "Contato Deletado Com sucesso.";

        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<ResponseMessage> alterarContato(@RequestBody CreateContato dto, @PathVariable Long id)
    {
        try
        {
            _service.AlterarContato(dto.toContato(), id);
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.messagem = "Alterado com sucesso.";
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.messagem = "Erro ao alterar contato.";
            return ResponseEntity.ok(responseMessage);
        }
    }

    @DeleteMapping("/deletar-por-prefixo")
    public ResponseEntity<ResponseMessage> deletarPorPrefixo(@RequestParam String prefixo) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            _service.DeletarContatosPorPrefixo(prefixo);
            responseMessage.messagem = "Contatos deletados com sucesso.";
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            responseMessage.messagem = "Erro ao deletar contatos: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}
