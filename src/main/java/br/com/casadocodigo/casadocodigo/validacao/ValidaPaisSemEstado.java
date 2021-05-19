package br.com.casadocodigo.casadocodigo.validacao;

import br.com.casadocodigo.casadocodigo.dto.ClienteDTO;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
@Component
public class ValidaPaisSemEstado implements Validator {


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
                List<Estado> listaEstados = estadoRepository.findEstadosNoPais(clienteDTO.getIdPais());
                if(listaEstados.size() == 0 && clienteDTO.getIdEstado() != null){
                    errors.rejectValue("idEstado", null, "O país informado não tem nenhum Estado cadastrado");
                }
            }
        }
    }