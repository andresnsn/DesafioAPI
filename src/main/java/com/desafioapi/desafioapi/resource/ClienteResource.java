package com.desafioapi.desafioapi.resource;

import com.desafioapi.desafioapi.model.Cliente;
import com.desafioapi.desafioapi.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "Clientes")
@RestController
@RequestMapping("api/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @ApiOperation("Retornar a lista de clientes")
    public List<Cliente> listarClientes(){ return clienteRepository.findAll();}

    @GetMapping("/{id}")
    @ApiOperation("Retornar um cliente pelo ID")
    public ResponseEntity<Cliente> buscarClientePeloId(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @GetMapping
    @RequestMapping("/asc")
    @ApiOperation("Listar os clientes em ordem alfabética crescente")
    public List<Cliente> listarClientesOrdemAlfaCresc(){ return clienteRepository.findAllByOrderByNomeAsc(); }

    @GetMapping
    @RequestMapping("/desc")
    @ApiOperation("Listar os clientes em ordem alfabética decrescente")
    public List<Cliente> listarClientesOrdemAlfaDecresc(){ return clienteRepository.findAllByOrderByNomeDesc(); }

    @PostMapping
    @ApiOperation("Inserir um novo cliente")
    public ResponseEntity<Cliente> adicionarCliente(@Valid @RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar os dados de um cliente")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.findById(id).orElse(null);
        if(clienteSalvo != null) {
            BeanUtils.copyProperties(cliente, clienteSalvo, "id");
            clienteRepository.save(clienteSalvo);
            return ResponseEntity.ok(clienteSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @RequestMapping("/nome/{nome}")
    @ApiOperation("Realizar a pesquisa de um cliente pelo nome")
    public List<Cliente> findAll(@PathVariable String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Deletar um cliente pelo ID")
    public void deletarCliente(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

}
