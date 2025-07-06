package com.exemplo.produto_api.service;

import com.exemplo.produto_api.dto.CategoriaRequestDTO;
import com.exemplo.produto_api.dto.CategoriaResponseDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository cR;

    @InjectMocks
    private CategoriaService categoriaService;

    @BeforeEach
    void setUp() {
        // opcional: MockitoAnnotations.openMocks(this); (já está com @ExtendWith)
    }

    @Test
    void deveCriarCategoriaComNomeValido() {
    
    
		    // -------- Arrange(preparação) ----------------------------------------------
		    // simulando q recebemos uma request p salvar uma nova categoria
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Eletrônicos");
        // simulando q a categoria foi salva no banco e recebeu ID 1
        Categoria categoriaSalva = new Categoria(1L, "Eletrônicos");
        
        // simulando q ainda não existe nenhuma categoria c esse nome no banco
        when(cR.existsByNome("Eletrônicos")).thenReturn(false);
        
        // simulando que, ao salvar no banco, o retorno será a categoria com ID 1
        when(cR.save(any(Categoria.class))).thenReturn(categoriaSalva);

        
        // -------- Act(acão) -------- ----------------------------------------
        //chamando o método a ser testado
        CategoriaResponseDTO resultado = categoriaService.criar(dto);


		// -------- Assert(verificação) ------------------------------------------- 
        assertNotNull(resultado);
        assertEquals("Eletrônicos", resultado.getNome());
        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoAoCriarCategoriaDuplicada() {
        // -------- Arrange(preparação) ----------------------------------------------
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Eletrônicos");

        //simular que já existe a categoria a ser salva
        when(cR.existsByNome("Eletrônicos")).thenReturn(true);

        // -------- Act(acão) -------- ----------------------------------------
        //chamando o método a ser testado
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            categoriaService.criar(dto);
        });

        // -------- Assert(verificação) ------------------------------------------- 
        assertEquals("Categoria já existe.", ex.getMessage());
        verify(cR, never()).save(any());
    }
    

    @Test
    void deveListarTodasCategorias() {
        List<Categoria> categorias = Arrays.asList(
                new Categoria(1L, "Livros"),
                new Categoria(2L, "Roupas")
        );

        when(cR.findAll()).thenReturn(categorias);

        List<CategoriaResponseDTO> resultado = categoriaService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Livros", resultado.get(0).getNome());
    }

    @Test
    void deveBuscarCategoriaPorId() {
        Categoria categoria = new Categoria(1L, "Roupas");

        when(cR.findById(1L)).thenReturn(Optional.of(categoria));

        CategoriaResponseDTO resultado = categoriaService.buscarPorId(1L);

        assertEquals("Roupas", resultado.getNome());
        assertEquals(1L, resultado.getId());
    }
      
    @Test
    void deveAtualizarCategoriaComSucesso() {
        // Arrange
        Categoria categoriaOriginal = new Categoria(1L, "Antigo");
        Categoria categoriaAtualizada = new Categoria(1L, "Novo");
        
        when(cR.findById(1L)).thenReturn(Optional.of(categoriaOriginal));
        when(cR.save(any(Categoria.class))).thenReturn(categoriaAtualizada);
        
        CategoriaRequestDTO request = new CategoriaRequestDTO("Novo");

        // Act
        CategoriaResponseDTO resultado = categoriaService.atualizar(1L, request);

        // Assert
        assertEquals("Novo", resultado.getNome());
        verify(cR, times(1)).save(any(Categoria.class));
    }

    @Test
    void deveDeletarCategoria() {
        when(cR.existsById(1L)).thenReturn(true);

        categoriaService.deletar(1L);

        verify(cR).deleteById(1L);
    }
    

    @Test
    void deveLancarExcecaoAoDeletarCategoriaInexistente() {
        when(cR.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> categoriaService.deletar(99L));

        verify(cR, never()).deleteById(any());
    }
}

