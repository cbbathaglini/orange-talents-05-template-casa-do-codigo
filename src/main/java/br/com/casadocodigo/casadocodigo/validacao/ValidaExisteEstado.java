package br.com.casadocodigo.casadocodigo.validacao;

import br.com.casadocodigo.casadocodigo.dto.ClienteDTO;
import br.com.casadocodigo.casadocodigo.dto.EstadoDTO;
import br.com.casadocodigo.casadocodigo.dto.PaisDTO;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ValidaExisteEstado implements Validator {


    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){ return; }
        ClienteDTO clienteDTO = (ClienteDTO) target;

        //se foi informado o país
        if(clienteDTO.getIdPais() != null) {
            System.out.println("PARTE 1");
            List<Estado> listaEstados = estadoRepository.findEstadosNoPais(clienteDTO.getIdPais());
            System.out.println("PARTE 2:");
            //se houver estados neste país
            if (listaEstados.size() > 0 && clienteDTO.getIdEstado() == null) {
                System.out.println("PARTE 3");
                errors.rejectValue("idEstado", null, "O estado não foi informado");
                System.out.println("O estado não foi informado");
            }
        }
    }
}