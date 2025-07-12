package com.exemplo.produto_api.repository;

import com.exemplo.produto_api.entity.Categoria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Deve salvar e buscar categoria por nome")
    void deveSalvarEBuscarCategoriaPorNome() {
        // Arrange
        Categoria categoria = new Categoria(null, "Eletrônicos");
        categoriaRepository.save(categoria);

        // Act
        Optional<Categoria> encontrado = categoriaRepository.findByNome("Eletrônicos");

        // Assert
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNome()).isEqualTo("Eletrônicos");
    }
}
