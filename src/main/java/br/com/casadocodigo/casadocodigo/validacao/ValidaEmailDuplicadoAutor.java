package br.com.casadocodigo.casadocodigo.validacao;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class ValidaEmailDuplicadoAutor implements Validator {

    //spring data jpa
    @Autowired
    private AutorRepository autorRepository;

    /* qual o tipo de parametro que devemos aplicar a validação */
    @Override
    public boolean supports(Class<?> clazz) {
        return AutorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //nenhum erro de validação até o momento
        if(errors.hasErrors()){ return; }
        AutorDTO autorDto = (AutorDTO) target;
        Optional<Autor> autorOp = autorRepository.findByEmail(autorDto.getEmail());
        if(autorOp.isPresent()){
            errors.rejectValue("email", null, "Já existe este email cadastrado na base de dados");
            System.out.println("Já existe este email na base de dados");
        }
    }
}
