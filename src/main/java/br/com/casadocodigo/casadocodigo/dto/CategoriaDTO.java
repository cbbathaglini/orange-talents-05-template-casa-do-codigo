package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaDTO {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "A categoria informada já existe")
    private String nome;

    public Categoria converter(){
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
