package com.desafioapi.desafioapi.resource;

import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    @ApiOperation("Listar todos os produtos")
    public List<Produto> listarProdutos(){ return produtoRepository.findAll(); }

    @GetMapping("/{id}")
    @ApiOperation("Retornar o produto pelo ID")
    public ResponseEntity<Produto> buscarPeloId(@PathVariable Long id){
        Produto produto = produtoRepository.findById(id).orElse(null);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @RequestMapping("/asc")
    @ApiOperation("Listar os produtos em ordem alfabética crescente")
    @GetMapping
    public List<Produto> listarProdutosOrdemAlfaCresc(){ return produtoRepository.findAllByOrderByNomeAsc(); }

    @GetMapping
    @RequestMapping("/desc")
    @ApiOperation("Listar os produtos em ordem alfabética decrescente")
    public List<Produto> listarProdutosOrdemAlfaDecresc(){ return produtoRepository.findAllByOrderByNomeDesc(); }

    @PostMapping
    @ResponseBody
    @ApiOperation("Inserir um novo produto")
    public ResponseEntity<Produto> inserirProduto(@Valid @RequestBody Produto produto){
        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar um produto")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto){
        Produto produtoSalvo = produtoRepository.findById(id).orElse(null);
        if(produtoSalvo != null) {
            BeanUtils.copyProperties(produto, produtoSalvo, "id");
            produtoRepository.save(produtoSalvo);
            return ResponseEntity.ok(produtoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    @RequestMapping("/nome/{nome}")
    @ApiOperation("Pesquisar um produto pelo nome")
    public List<Produto> findAll(@PathVariable String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Excluir um produto")
    public void excluirProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);

    }

}
