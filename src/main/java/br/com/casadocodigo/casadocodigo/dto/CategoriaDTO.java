package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaDTO {

    @NotBlank
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
