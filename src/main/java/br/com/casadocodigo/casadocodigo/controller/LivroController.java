package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.dto.LivroDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid LivroDTO livroDto){
        Livro livro = livroDto.converter();
        livroRepository.save(livro);
        return ResponseEntity.ok().build();
    }
}