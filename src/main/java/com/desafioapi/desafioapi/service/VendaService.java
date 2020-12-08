package com.desafioapi.desafioapi.service;

import com.desafioapi.desafioapi.model.Fornecedor;
import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.model.Venda;
import com.desafioapi.desafioapi.repository.FornecedorRepository;
import com.desafioapi.desafioapi.repository.VendaRepository;
import com.desafioapi.desafioapi.service.exception.ProductInsertionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    public Venda salvar(Venda venda) {
        Venda vendaSalva = vendaRepository.save(venda);
        List<String> checkList = new ArrayList<>();
        for(Produto produto : vendaSalva.getProdutos()){
            int idProduto = produto.getId().intValue();
            Fornecedor fornecedor = fornecedorRepository.findById(vendaSalva.getFornecedor().getId()).orElse(null);
            for(Produto produtos : fornecedor.getProdutos()){
                if(idProduto != produtos.getId().intValue()){
                    System.out.println("Produto não bate!");
                } else {
                    System.out.println("Produto OK!");
                    checkList.add("OK");
                }
            }
        }
        if(checkList.size() != vendaSalva.getProdutos().size()){
            System.out.println("Há algo errado com os produtos");
            vendaRepository.deleteById(vendaSalva.getId());
            throw new ProductInsertionException();
        } else {
            System.out.println("Todos os produtos batem!");
        }
        return vendaRepository.save(vendaSalva);
    }
}
