package com.contatamento.contatos_api.Interfaces;

import com.contatamento.contatos_api.Models.Contato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContatoRepository extends CrudRepository<Contato, Long> {

}