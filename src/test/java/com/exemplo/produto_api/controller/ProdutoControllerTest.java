package com.exemplo.produto_api.controller;

import com.exemplo.produto_api.dto.ProdutoRequestDTO;
import com.exemplo.produto_api.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

  /*  @Test
    void deveListarProdutos() throws Exception {
        ProdutoRequestDTO produto = new ProdutoRequestDTO();
        produto.setId(1L);
        produto.setNome("Feijão");
        produto.setPreco(new Double("9.99"));
        produto.setQuantidade(10);
        produto.setCategoriaId(1L);

        when(produtoService.listar()).thenReturn(List.of(produto));

        mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Feijão"))
            .andExpect(jsonPath("$[0].preco").value(9.99));
    }*/
}