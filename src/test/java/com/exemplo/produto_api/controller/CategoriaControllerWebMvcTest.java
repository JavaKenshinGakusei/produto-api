package com.exemplo.produto_api.controller;

import com.exemplo.produto_api.dto.CategoriaRequestDTO;
import com.exemplo.produto_api.dto.CategoriaResponseDTO;
import com.exemplo.produto_api.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CategoriaService categoriaService;

    @Test
    void deveListarCategorias() throws Exception {
        List<CategoriaResponseDTO> categorias = List.of(
                new CategoriaResponseDTO(1L, "Livros"),
                new CategoriaResponseDTO(2L, "Jogos")
        );

        when(categoriaService.listar()).thenReturn(categorias);

        mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Livros"))
                .andExpect(jsonPath("$[1].nome").value("Jogos"));
    }

    @Test
    void deveCriarCategoriaComNomeValido() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Eletrônicos");
        CategoriaResponseDTO response = new CategoriaResponseDTO(1L, "Eletrônicos");

        when(categoriaService.criar(any(CategoriaRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Eletrônicos"));
    }

    @Test
    void deveBuscarCategoriaPorId() throws Exception {
        CategoriaResponseDTO response = new CategoriaResponseDTO(1L, "Roupas");

        when(categoriaService.buscarPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Roupas"));
    }

    @Test
    void deveAtualizarCategoria() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Atualizado");
        CategoriaResponseDTO response = new CategoriaResponseDTO(1L, "Atualizado");

        when(categoriaService.atualizar(eq(1L), any(CategoriaRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/categorias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Atualizado"));
    }

    @Test
    void deveDeletarCategoria() throws Exception {
        doNothing().when(categoriaService).deletar(1L);

        mockMvc.perform(delete("/categorias/1"))
                .andExpect(status().isNoContent());
    }
}
