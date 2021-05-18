package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.EstadoDTO;
import br.com.casadocodigo.casadocodigo.dto.PaisDTO;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.model.Pais;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import br.com.casadocodigo.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastra(@RequestBody @Valid PaisDTO paisDTO){
        paisRepository.save(new Pais(paisDTO.getNome()));
        return ResponseEntity.ok().build();
    }

}