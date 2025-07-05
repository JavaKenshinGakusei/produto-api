package com.exemplo.produto_api.controller;

import com.exemplo.produto_api.dto.CategoriaRequestDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        categoriaRepository.deleteAll(); // Limpa o banco antes de cada teste
    }

    @Test
    void deveCriarNovaCategoria() throws Exception {
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Eletrônicos");

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Eletrônicos"));
    }

    @Test
    void deveListarCategorias() throws Exception {
        categoriaRepository.save(new Categoria(null, "Livros"));
        categoriaRepository.save(new Categoria(null, "Esportes"));

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    void deveBuscarCategoriaPorId() throws Exception {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Roupas"));

        mockMvc.perform(get("/categorias/" + categoria.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Roupas"));
    }

    @Test
    void deveAtualizarCategoria() throws Exception {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Antigo"));

        CategoriaRequestDTO dto = new CategoriaRequestDTO("Novo");

        mockMvc.perform(put("/categorias/" + categoria.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo"));
    }

    @Test
    void deveDeletarCategoria() throws Exception {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Apagar"));

        mockMvc.perform(delete("/categorias/" + categoria.getId()))
                .andExpect(status().isNoContent());
    }
}