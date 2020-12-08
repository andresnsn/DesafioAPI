package com.desafioapi.desafioapi.resource;

import com.desafioapi.desafioapi.model.Fornecedor;
import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.model.Venda;
import com.desafioapi.desafioapi.repository.FornecedorRepository;
import com.desafioapi.desafioapi.repository.VendaRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Vendas")
@RestController
@RequestMapping("api/vendas")
public class VendaResource {

    @Autowired
    private VendaRepository vendasRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<Venda> listarVendas() { return vendasRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId (@PathVariable Long id) {
        Venda vendaSalva = vendasRepository.findById(id).orElse(null);
        return vendaSalva != null ? ResponseEntity.ok(vendaSalva) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venda> novaVenda(@Valid @RequestBody Venda venda) {
        Venda vendaSalva = vendasRepository.save(venda);
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
        } else {
            System.out.println("Todos os produtos batem!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(@PathVariable Long id, @Valid @RequestBody Venda venda) {
        Venda vendaSalva = vendasRepository.findById(id).orElse(null);
        if(vendaSalva != null) {
            BeanUtils.copyProperties(venda, vendaSalva, "id");
            vendasRepository.save(vendaSalva);
            return ResponseEntity.ok(vendaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarVenda(@PathVariable Long id) {
        vendasRepository.deleteById(id);
    }



}
