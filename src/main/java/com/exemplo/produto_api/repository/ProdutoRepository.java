package com.exemplo.produto_api.repository;



import com.exemplo.produto_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;



public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}