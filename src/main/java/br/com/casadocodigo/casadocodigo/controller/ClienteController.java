package br.com.casadocodigo.casadocodigo.controller;

import br.com.casadocodigo.casadocodigo.dto.ClienteDTO;
import br.com.casadocodigo.casadocodigo.dtoresponse.ClienteDTOResponse;
import br.com.casadocodigo.casadocodigo.model.Cliente;
import br.com.casadocodigo.casadocodigo.validacao.ValidaExisteEstadoNoPais;
import br.com.casadocodigo.casadocodigo.validacao.ValidaNaoInformouEstado;
import br.com.casadocodigo.casadocodigo.validacao.ValidaPaisContemEstado;
import br.com.casadocodigo.casadocodigo.validacao.ValidaPaisSemEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ValidaNaoInformouEstado validaNaoInformouEstado;

    @Autowired
    private ValidaPaisSemEstado validaPaisSemEstado;

    @Autowired
    private ValidaPaisContemEstado validaPaisContemEstado;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(validaNaoInformouEstado, validaPaisSemEstado,validaPaisContemEstado);
    }


    @Transactional
    @PostMapping
    public ResponseEntity<ClienteDTOResponse> cadastra(@RequestBody @Valid ClienteDTO clienteDTO){
        Cliente cliente = clienteDTO.converter(em);
        if(cliente != null) {
            em.persist(cliente);
            //System.out.println("Cliente: ");
            //System.out.println(cliente.toString());
            return ResponseEntity.ok(new ClienteDTOResponse(cliente));
        }
        return ResponseEntity.notFound().build();
    }
}
