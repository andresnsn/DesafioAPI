package com.desafioapi.desafioapi.resource;

import com.desafioapi.desafioapi.model.Fornecedor;
import com.desafioapi.desafioapi.repository.FornecedorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Fornecedores")
@RestController
@RequestMapping("api/fornecedores")
public class FornecedorResource {


    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    @ApiOperation("Retornar a lista de fornecedores")
    public List<Fornecedor> listarFornecedores() { return fornecedorRepository.findAll(); }

    @GetMapping("/{id}")
    @ApiOperation("Retornar um fornecedor pelo ID")
    public ResponseEntity<Fornecedor> fornecedorPeloId(@PathVariable Long id) {
        Fornecedor fornecedorSalvo = fornecedorRepository.findById(id).orElse(null);
        return fornecedorSalvo != null ? ResponseEntity.ok(fornecedorSalvo) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @RequestMapping("/asc")
    @ApiOperation("Listar os fornecedores em ordem alfabética crescente")
    public List<Fornecedor> listarFornecedoresOrdemAlfaCresc(){ return fornecedorRepository.findAllByOrderByNomeAsc(); }

    @GetMapping
    @RequestMapping("/desc")
    @ApiOperation("Listar os fornecedores em ordem alfabética decrescente")
    public List<Fornecedor> listarFornecedoresOrdemAlfaDecresc(){ return fornecedorRepository.findAllByOrderByNomeDesc(); }

    @PostMapping
    @ApiOperation("Inserir um novo fornecedor")
    public ResponseEntity<Fornecedor> novoFornecedor(@Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar os dados de um fornecedor")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id, @Valid @RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorRepository.findById(id).orElse(null);
        if(fornecedorSalvo != null) {
            BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "id");
            fornecedorRepository.save(fornecedorSalvo);
            return ResponseEntity.ok(fornecedorSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    @RequestMapping("/nome/{nome}")
    @ApiOperation("Pesquisar um fornecedor pelo nome")
    public List<Fornecedor> findAll(@PathVariable String nome) {
        return fornecedorRepository.findByNomeContaining(nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Deletar um fornecedor pelo ID")
    public void deletarPorId(@PathVariable Long id) {
        fornecedorRepository.deleteById(id);
    }
}
