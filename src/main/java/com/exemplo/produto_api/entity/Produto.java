package com.exemplo.produto_api.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

	
 // Getters e Setters
    public Long getId() { 	return id;	}
    
    public String getNome() { return nome; 	}
	public void setNome(String nome) { 	this.nome = nome;	}

	public BigDecimal getPreco() { return preco;	}
	public void setPreco(BigDecimal preco) { this.preco = preco;	}

	public Integer getQuantidade() { 		return quantidade;	}
	public void setQuantidade(Integer quantidade) {		this.quantidade = quantidade;	}

	public Categoria getCategoria() { 		return categoria;	}
	public void setCategoria(Categoria categoria) { 		this.categoria = categoria;	}

	

    
    
}