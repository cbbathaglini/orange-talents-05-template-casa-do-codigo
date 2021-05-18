package br.com.casadocodigo.casadocodigo.validacao;

import br.com.casadocodigo.casadocodigo.dto.AutorDTO;
import br.com.casadocodigo.casadocodigo.dto.EstadoDTO;
import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class ValidaEstadoPais implements Validator {


    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){ return; }

        EstadoDTO estadoDTO = (EstadoDTO) target;
        List<Estado> listaEstados = estadoRepository.findEstadoByNomeNoPais(estadoDTO.getNome(), estadoDTO.getIdPais());

        if(listaEstados.size() > 0){
            errors.rejectValue("nome", null, "Já existe um estado com o nome informado neste país");
            System.out.println("Já existe um estado com o nome informado neste país");
        }
    }
}
