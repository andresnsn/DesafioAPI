package com.desafioapi.desafioapi.repository;

import com.desafioapi.desafioapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByOrderByNomeAsc();

    List<Cliente> findAllByOrderByNomeDesc();

    List<Cliente> findByNomeContaining(String nome);
}
