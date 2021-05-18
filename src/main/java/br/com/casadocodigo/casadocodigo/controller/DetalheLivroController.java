package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dtoresponse.DetalheLivroDTOResponse;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@RestController
@RequestMapping("/livroDetalhe")
public class DetalheLivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {

        Optional<Livro> livroOp = livroRepository.findById(id);

        if (livroOp.isPresent()) {
            DetalheLivroDTOResponse detalheSiteLivroResponse = new DetalheLivroDTOResponse(livroOp.get());
            return ResponseEntity.ok(detalheSiteLivroResponse);
        }

        return ResponseEntity.notFound().build();
    }
}
