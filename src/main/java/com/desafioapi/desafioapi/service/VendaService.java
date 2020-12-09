package com.desafioapi.desafioapi.service;

import com.desafioapi.desafioapi.model.Fornecedor;
import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.model.Venda;
import com.desafioapi.desafioapi.repository.FornecedorRepository;
import com.desafioapi.desafioapi.repository.VendaRepository;
import com.desafioapi.desafioapi.service.exception.ProductInsertionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    Double soma = 0.0;

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
            vendaRepository.deleteById(vendaSalva.getId());
            throw new ProductInsertionException();
        }
        List<Venda> testeVenda = vendaRepository.findAll();
        return vendaRepository.save(vendaSalva);
    }
    public void totalCompra(Venda venda){
        List<Produto> produtos = venda.getProdutos();
        Venda vendaFinalCalc = vendaRepository.findById(venda.getId()).get();
        for(Produto produto : produtos) {

            if(produto.getPromocao()) {
                soma = soma + produto.getValor_promo().doubleValue();
            } else {
                soma = soma + produto.getValor().doubleValue();
            }
        }
        BigDecimal somaFinal = new BigDecimal(soma, MathContext.DECIMAL64);
        venda.setTotal_compra(somaFinal);
        BeanUtils.copyProperties(venda, vendaFinalCalc, "id");
        vendaRepository.save(venda);
        soma = 0.0;
    }

    }

