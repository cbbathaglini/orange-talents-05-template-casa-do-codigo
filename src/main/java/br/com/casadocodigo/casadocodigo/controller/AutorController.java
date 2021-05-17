package br.com.casadocodigo.casadocodigo.controller;


import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.validacao.ValidaEmailDuplicadoAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/autores")
public class AutorController {

    //@PersistenceContext
    //private EntityManager em;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ValidaEmailDuplicadoAutor emailDuplicadoAutorValidator;

    /* fazer configurações adicionais e customizadas. utilizado no primeiro request */
    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(emailDuplicadoAutorValidator);
    }

    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid AutorDTO autorDto){
        Autor autor = autorDto.converter();
        //System.out.println(autor.toString());
        autorRepository.save(autor);
        return ResponseEntity.ok().build();
    }
}
