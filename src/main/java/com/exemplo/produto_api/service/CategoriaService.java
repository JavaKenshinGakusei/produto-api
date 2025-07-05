package com.exemplo.produto_api.service;


import com.exemplo.produto_api.dto.CategoriaRequestDTO;
import com.exemplo.produto_api.dto.CategoriaResponseDTO;
import com.exemplo.produto_api.entity.Categoria;
import com.exemplo.produto_api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {     this.repository = repository;    }

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        if (repository.existsByNome(dto.getNome())) { throw new RuntimeException("Categoria já existe com esse nome.");       }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());

        Categoria salva = repository.save(categoria);

        return new CategoriaResponseDTO(salva.getId(), salva.getNome());
    }

    
    
    public List<CategoriaResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(cat -> new CategoriaResponseDTO(cat.getId(), cat.getNome()))
                .collect(Collectors.toList());
    }

    
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }

   
    
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));

        categoria.setNome(dto.getNome());

        Categoria atualizada = repository.save(categoria);

        return new CategoriaResponseDTO(atualizada.getId(), atualizada.getNome());
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada.");
        }

        repository.deleteById(id);
    }
}
