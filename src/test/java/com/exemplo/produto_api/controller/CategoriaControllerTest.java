package com.exemplo.produto_api.controller;

import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoriaRepository categoriaRepository;

    @Test
    void deveListarCategorias() throws Exception {
        Categoria cat = new Categoria();
        cat.setId(1L);
        cat.setNome("Alimentos");

        when(categoriaRepository.findAll()).thenReturn(List.of(cat));

        mockMvc.perform(get("/categorias"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nome").value("Alimentos"));
    }
}
