package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.dto.LivroDTO;
import br.com.casadocodigo.casadocodigo.dtoresponse.LivroDTOResponse;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid LivroDTO livroDto){
        Livro livro = livroDto.converter(entityManager);
        livroRepository.save(livro);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public List<LivroDTOResponse> listar(){
        List<Livro> listaLivros = (List<Livro>) livroRepository.findAll();
        LivroDTOResponse livroDTOResponse = new LivroDTOResponse();
        return livroDTOResponse.converterLista(listaLivros);
    }







}