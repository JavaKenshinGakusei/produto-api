package com.exemplo.produto_api.service;

import com.exemplo.produto_api.dto.ProdutoRequestDTO;
import com.exemplo.produto_api.dto.ProdutoResponseDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.entity.Produto;
import com.exemplo.produto_api.repository.CategoriaRepository;
import com.exemplo.produto_api.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarProdutoComCategoriaExistente() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("TV", 2000.0, 5, 1L);
        Categoria categoria = new Categoria(1L, "Eletrônicos");

        Produto produtoSalvo = new Produto(1L, "TV", 2000.0, 5, categoria);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        ProdutoResponseDTO resultado = produtoService.criar(dto);

        assertNotNull(resultado);
        assertEquals("TV", resultado.getNome());
        assertEquals("Eletrônicos", resultado.getCategoriaNome());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExiste() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("TV", 2000.0, 5, 999L);

        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            produtoService.criar(dto);
        });

        assertEquals("Categoria não encontrada", ex.getMessage());
    }



		
    @Test
		void deveAtualizarProdutoComDadosValidos() {
	    ProdutoRequestDTO dto = new ProdutoRequestDTO("TV 4K", 3000.0, 3, 1L);
	    Categoria categoria = new Categoria(1L, "Eletrônicos");

	    Produto existente = new Produto(1L, "TV", 2000.0, 5, categoria);
	    Produto atualizado = new Produto(1L, "TV 4K", 3000.0, 3, categoria);

	    when(produtoRepository.findById(1L)).thenReturn(Optional.of(existente));
	    when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
	    when(produtoRepository.save(any(Produto.class))).thenReturn(atualizado);

	    ProdutoResponseDTO resultado = produtoService.atualizar(1L, dto);

	    assertEquals("TV 4K", resultado.getNome());
	    assertEquals(3000.0, resultado.getPreco());
	    assertEquals(3, resultado.getQuantidade());
	}
	
	
	
	
	
	//❌ Teste para falha ao atualizar produto inexistente
	@Test
	void deveLancarExcecaoAoAtualizarProdutoInexistente() {
	    ProdutoRequestDTO dto = new ProdutoRequestDTO("Notebook", 4000.0, 2, 1L);

	    when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

	    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
	        produtoService.atualizar(999L, dto);
	    });

	    assertEquals("Produto não encontrado", ex.getMessage());
	}



  //Teste para buscar produto por ID
	@Test
	void deveBuscarProdutoPorId() {
    Categoria categoria = new Categoria(1L, "Livros");
    Produto produto = new Produto(1L, "Livro Java", 120.0, 10, categoria);

    when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

    ProdutoResponseDTO resultado = produtoService.buscarPorId(1L);

    assertEquals("Livro Java", resultado.getNome());
    assertEquals("Livros", resultado.getCategoriaNome());
	}
	
	
	//❌ Teste para buscar produto inexistente
	@Test
	void deveLancarExcecaoAoBuscarProdutoInexistente() {
    when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
        produtoService.buscarPorId(999L);
    });

    assertEquals("Produto não encontrado", ex.getMessage());
}


	//📃 Teste para listar todos os produtos
	@Test
	void deveListarTodosProdutos() {
    Categoria categoria = new Categoria(1L, "Games");

    List<Produto> produtos = List.of(
            new Produto(1L, "Console", 2000.0, 5, categoria),
            new Produto(2L, "Controle", 300.0, 10, categoria)
    );

    when(produtoRepository.findAll()).thenReturn(produtos);

    List<ProdutoResponseDTO> resultado = produtoService.listar();

    assertEquals(2, resultado.size());
	}


	//🗑️ Teste para remover produto
	@Test
	void deveRemoverProdutoExistente() {
    when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto()));

    produtoService.remover(1L);

    verify(produtoRepository).deleteById(1L);
	}


	//❌ Teste para falha ao remover produto inexistente
	@Test
	void deveLancarExcecaoAoRemoverProdutoInexistente() {
    when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

    RuntimeException ex = assertThrows(RuntimeException.class, () -> {
        produtoService.remover(999L);
    });

    assertEquals("Produto não encontrado", ex.getMessage());
	}

}