package com.exemplo.produto_api.service;


import com.exemplo.produto_api.dto.ProdutoRequestDTO;
import com.exemplo.produto_api.dto.ProdutoResponseDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.entity.Produto;
import com.exemplo.produto_api.repository.CategoriaRepository;
import com.exemplo.produto_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto(null, dto.getNome(), dto.getPreco(), dto.getQuantidade(), categoria);
        Produto salvo = produtoRepository.save(produto);

        return toResponse(salvo);
    }

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return toResponse(produto);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setCategoria(categoria);

        Produto atualizado = produtoRepository.save(produto);
        return toResponse(atualizado);
    }

    public void remover(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoResponseDTO toResponse(Produto p) {
        return new ProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getQuantidade(),
                p.getCategoria().getNome()
        );
    }
}
