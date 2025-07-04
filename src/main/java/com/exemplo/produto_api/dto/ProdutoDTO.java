package com.exemplo.produto_api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal preco;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @NotNull
    private Long categoriaId;
    
    
    //  ♦ ♦ ♦ Getters e Setters ♦ ♦ ♦
    public Long getId() {		return id;	}
	public void setId(Long id) {		this.id= id;		}
    
	public String getNome() { 		return nome; 	}
	public void setNome(String nome) { 		this.nome = nome; 	}

	public BigDecimal getPreco() { 		return preco;	}
	public void setPreco(BigDecimal preco) {		this.preco = preco;	}

	public Integer getQuantidade() {		return quantidade;	}
	public void setQuantidade(Integer quantidade) {		this.quantidade = quantidade;	}

	public Long getCategoriaId() {  return categoriaId;	}
	public void setCategoriaId(Long categoriaId) {	this.categoriaId = categoriaId;	}

	

        
}