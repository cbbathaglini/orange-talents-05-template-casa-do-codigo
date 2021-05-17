package br.com.casadocodigo.casadocodigo.validacao;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.dto.CategoriaDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ValidaNomeCategoriaDuplicada implements Validator {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){ return; }

        CategoriaDTO categoriaDto = (CategoriaDTO) target;
        Optional<Categoria> autorOp = categoriaRepository.findByNome(categoriaDto.getNome());
        if(autorOp.isPresent()){
            errors.rejectValue("nome", null, "Já existe uma categoria com este nome na base de dados");
            System.out.println("Já existe uma categoria com este nome na base de dados");
        }
    }
}
