package br.com.casadocodigo.casadocodigo.controller;


import br.com.casadocodigo.casadocodigo.dto.EstadoDTO;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import br.com.casadocodigo.casadocodigo.validacao.ValidaExisteEstadoNoPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ValidaExisteEstadoNoPais estadoPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(estadoPaisValidator);
    }


    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid EstadoDTO estadoDTO){
        System.out.println(estadoDTO.toString());
        System.out.println("-----------------------------");
        Estado estado = estadoDTO.converter(em,estadoRepository);
        if(estado != null) {
            em.persist(estado);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
