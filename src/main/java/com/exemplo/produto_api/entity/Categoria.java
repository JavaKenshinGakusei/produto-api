package com.exemplo.produto_api.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;
    
    public Categoria() {}
    
        
    public Categoria(Long id, @NotBlank String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
    
    
    
// Getters e Setters
    public void setId(long l) { 		this.id=l;			}
    public Long getId() {		return id;	}

	public String getNome() { return nome;	}
	public void setNome(String nome) { 	this.nome = nome;}

	

	

    
    
}
