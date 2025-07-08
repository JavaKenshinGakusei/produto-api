package com.exemplo.produto_api.dto;

import jakarta.validation.constraints.*;

public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Double preco;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Long categoriaId;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(String nome, Double preco, Integer quantidade, Long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoriaId = categoriaId;
    }
    
    // Getters e setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

    
    
    
}