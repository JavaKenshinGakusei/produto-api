package com.exemplo.produto_api.repository;


import com.exemplo.produto_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}