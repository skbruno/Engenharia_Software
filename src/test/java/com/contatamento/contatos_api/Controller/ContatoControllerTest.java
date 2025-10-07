package com.contatamento.contatos_api.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.contatamento.contatos_api.Controllers.ContatoController;
import com.contatamento.contatos_api.Controllers.Dtos.ContatoResponse;
import com.contatamento.contatos_api.Service.ContatoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContatoController.class)
class ContatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContatoService contatoService;

    @Test
    void RetornarContatoPorId() throws Exception
    {
        ContatoResponse contato = new ContatoResponse(1L, "Bruno", 71981997473L);
        when(contatoService.BuscarContatoId(1L)).thenReturn(contato);

        mockMvc.perform(get("/contatos/by-id").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Bruno"))
                .andExpect(jsonPath("$.numero").value(71981997473L));
    }


    @Test
    void RetornarContatoPorIdNotFound() throws Exception
    {
        when(contatoService.BuscarContatoId(2L)).thenReturn(null);

        mockMvc.perform(get("/contatos/by-id").param("id", "2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem").value("Contato não encontrado"));
    }
}