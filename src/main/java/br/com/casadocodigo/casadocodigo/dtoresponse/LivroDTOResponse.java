package br.com.casadocodigo.casadocodigo.dtoresponse;

import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class LivroDTOResponse {

    private Long id;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "O título informado já existe")
    private String titulo;

    public LivroDTOResponse() {
    }

    public LivroDTOResponse(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<LivroDTOResponse> converterLista(List<Livro> listaLivros) {
        List<LivroDTOResponse> livros = new ArrayList<>();
        for (Livro livro:listaLivros) {
            livros.add(new LivroDTOResponse(livro.getId(), livro.getTitulo()));
            //System.out.println("titulo: "+livro.getTitulo()+"\n");
        }
        return livros;
    }
}
