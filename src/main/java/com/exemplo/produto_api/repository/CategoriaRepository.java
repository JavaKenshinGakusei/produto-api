package com.exemplo.produto_api.repository;


import com.exemplo.produto_api.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	boolean existsByNome(String nome);
}