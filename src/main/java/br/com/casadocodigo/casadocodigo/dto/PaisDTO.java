package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.model.Pais;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisDTO {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "O país informado já existe")
    private String nome;

    public PaisDTO() {
    }

    public PaisDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
