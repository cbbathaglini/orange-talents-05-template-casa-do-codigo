package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.ClienteDTO;
import br.com.casadocodigo.casadocodigo.dto.EstadoDTO;
import br.com.casadocodigo.casadocodigo.model.Cliente;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.repository.ClienteRepository;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import br.com.casadocodigo.casadocodigo.validacao.ValidaEstadoPais;
import br.com.casadocodigo.casadocodigo.validacao.ValidaExisteEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ValidaExisteEstado existeEstadoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(existeEstadoValidator);
    }


    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid ClienteDTO clienteDTO){
        Cliente cliente = clienteDTO.converter(em);
        if(cliente != null) {
            em.persist(cliente);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
