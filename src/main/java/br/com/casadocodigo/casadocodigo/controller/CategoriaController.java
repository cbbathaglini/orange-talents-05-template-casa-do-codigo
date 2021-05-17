package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.dto.CategoriaDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.repository.CategoriaRepository;
import br.com.casadocodigo.casadocodigo.validacao.ValidaNomeCategoriaDuplicada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*@Autowired
    private ValidaNomeCategoriaDuplicada categoriaDuplicaValidator;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(categoriaDuplicaValidator);
    }
     */

    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaDTO.converter();
        //System.out.println(autor.toString());
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }
}
