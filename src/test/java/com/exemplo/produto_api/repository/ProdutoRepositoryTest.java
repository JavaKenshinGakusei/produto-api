package com.exemplo.produto_api.repository;

import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.entity.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Deve salvar e buscar produto corretamente")
    void deveSalvarEBuscarProduto() {
        // Arrange
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Doces"));

        Produto produto = new Produto(null, "Chocolate", Double.valueOf(9.99), 10, categoria);
        produtoRepository.save(produto);

        // Act
        List<Produto> encontrados = produtoRepository.findAll();

        // Assert
        assertThat(encontrados).hasSize(1);
        assertThat(encontrados.get(0).getNome()).isEqualTo("Chocolate");
        assertThat(encontrados.get(0).getPreco()).isEqualTo("9.99");
    }
}