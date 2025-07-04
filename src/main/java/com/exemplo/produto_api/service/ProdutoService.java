package com.exemplo.produto_api.service;



import com.exemplo.produto_api.dto.ProdutoDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.entity.Produto;
import com.exemplo.produto_api.repository.CategoriaRepository;
import com.exemplo.produto_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setCategoria(categoria);

        produto = produtoRepository.save(produto);

        return toDTO(produto);
    }

    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());
        dto.setCategoriaId(produto.getCategoria().getId());
        return dto;
    }
}

