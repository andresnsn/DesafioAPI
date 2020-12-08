package com.desafioapi.desafioapi.resource;

import com.desafioapi.desafioapi.model.Fornecedor;
import com.desafioapi.desafioapi.model.Produto;
import com.desafioapi.desafioapi.model.Venda;
import com.desafioapi.desafioapi.repository.FornecedorRepository;
import com.desafioapi.desafioapi.repository.VendaRepository;
import com.desafioapi.desafioapi.service.VendaService;
import com.desafioapi.desafioapi.service.exception.ProductInsertionException;
import io.swagger.annotations.Api;
import org.mapstruct.util.Experimental;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(tags = "Vendas")
@RestController
@RequestMapping("api/vendas")
public class VendaResource {

    @Autowired
    private VendaRepository vendasRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private VendaService vendaService;

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public List<Venda> listarVendas() { return vendasRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVendaPorId (@PathVariable Long id) {
        Venda vendaSalva = vendasRepository.findById(id).orElse(null);
        return vendaSalva != null ? ResponseEntity.ok(vendaSalva) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venda> novaVenda(@Valid @RequestBody Venda venda) {
        Venda vendaSalva = vendaService.salvar(venda);
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

    @ExceptionHandler({ProductInsertionException.class})
    public ResponseEntity<Object> handleProductInsertException(ProductInsertionException ex) {
        String userMessage = messageSource.getMessage("product.insert-condition", null, LocaleContextHolder.getLocale());
        String developerMessage = ex.toString();
        List<com.desafioapi.desafioapi.exceptionhandler.ExceptionHandler.Error> errors = Arrays.asList(new com.desafioapi.desafioapi.exceptionhandler.ExceptionHandler.Error(userMessage, developerMessage));
        return ResponseEntity.badRequest().body(errors);
        }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarVenda(@PathVariable Long id) {
        vendasRepository.deleteById(id);
    }

}
