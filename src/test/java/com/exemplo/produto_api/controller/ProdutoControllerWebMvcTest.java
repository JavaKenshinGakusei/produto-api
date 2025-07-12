package com.exemplo.produto_api.controller;

import com.exemplo.produto_api.dto.ProdutoRequestDTO;
import com.exemplo.produto_api.dto.ProdutoResponseDTO;
import com.exemplo.produto_api.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarProduto() throws Exception {
        ProdutoRequestDTO request = new ProdutoRequestDTO("Celular", 1500.0, 10, 1L);
        ProdutoResponseDTO response = new ProdutoResponseDTO(1L, "Celular", 1500.0, 10, "Eletrônicos");

        Mockito.when(produtoService.criar(any())).thenReturn(response);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Celular"))
                .andExpect(jsonPath("$.categoriaNome").value("Eletrônicos"));
    }

    @Test
    void deveListarProdutos() throws Exception {
        List<ProdutoResponseDTO> lista = List.of(
                new ProdutoResponseDTO(1L, "Notebook", 2500.0, 5, "Informática"),
                new ProdutoResponseDTO(2L, "Mouse", 80.0, 20, "Informática")
        );

        Mockito.when(produtoService.listar()).thenReturn(lista);

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        ProdutoResponseDTO response = new ProdutoResponseDTO(1L, "Monitor", 999.0, 3, "Informática");

        Mockito.when(produtoService.buscarPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Monitor"));
    }

    @Test
    void deveAtualizarProduto() throws Exception {
        ProdutoRequestDTO request = new ProdutoRequestDTO("Atualizado", 1800.0, 4, 1L);
        ProdutoResponseDTO response = new ProdutoResponseDTO(1L, "Atualizado", 1800.0, 4, "Informática");

        Mockito.when(produtoService.atualizar(Mockito.eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/produtos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Atualizado"))
                .andExpect(jsonPath("$.preco").value(1800.0));
    }

    @Test
    void deveDeletarProduto() throws Exception {
        Mockito.doNothing().when(produtoService).remover(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }
}