package com.desafioapi.desafioapi.repository;

import com.desafioapi.desafioapi.model.Cliente;
import com.desafioapi.desafioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByOrderByNomeAsc();

    List<Produto> findAllByOrderByNomeDesc();

    List<Produto> findByNomeContaining(String nome);

}
