package br.com.casadocodigo.casadocodigo.dtoresponse;

import br.com.casadocodigo.casadocodigo.model.Autor;

public class DetalheAutorDTOResponse {
    private String nome;
    private String descricao;

    public DetalheAutorDTOResponse(Autor autor) {
        nome = autor.getNome();
        descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
