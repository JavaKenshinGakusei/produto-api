package com.exemplo.produto_api.repository;


import com.exemplo.produto_api.entity.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	Optional<Categoria> findByNome(String nome);
	
	boolean existsByNome(String nome);
}