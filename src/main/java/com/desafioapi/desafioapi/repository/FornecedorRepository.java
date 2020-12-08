package com.desafioapi.desafioapi.repository;

import com.desafioapi.desafioapi.model.Cliente;
import com.desafioapi.desafioapi.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findAllByOrderByNomeAsc();

    List<Fornecedor> findAllByOrderByNomeDesc();

    List<Fornecedor> findByNomeContaining(String nome);
}
