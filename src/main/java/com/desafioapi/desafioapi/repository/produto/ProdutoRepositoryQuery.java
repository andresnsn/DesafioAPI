package com.desafioapi.desafioapi.repository.produto;

import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.repository.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoRepositoryQuery {

    public Page<Produto> filter(ProdutoFilter produtoFilter, Pageable pageable);
}
