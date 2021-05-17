package br.com.casadocodigo.casadocodigo.controller;


import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid AutorDTO autorDto){
        Autor autor = autorDto.converter();
        //System.out.println(autor.toString());
        em.persist(autor);

        return ResponseEntity.ok().build();
    }
}
