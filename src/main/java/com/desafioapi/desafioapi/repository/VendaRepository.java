package com.desafioapi.desafioapi.repository;

import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.model.ProdutoRetriever;
import com.desafioapi.desafioapi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findAll();
}
