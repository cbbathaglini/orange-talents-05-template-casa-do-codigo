package br.com.casadocodigo.casadocodigo.dtoresponse;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Livro;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DetalheLivroDTOResponse {
    private String titulo;
    private BigDecimal preco;
    private String dataPublicacao;
    private Integer numeroPaginas;
    private String ISBN; //International Standard Book Number
    private String resumo;
    private String sumario;

    private DetalheAutorDTOResponse autor;

    public DetalheLivroDTOResponse(Livro livro) {
        titulo = livro.getTitulo();
        preco = livro.getPreco();
        dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        numeroPaginas = livro.getNumeroPaginas();
        ISBN = livro.getISBN();
        resumo = livro.getResumo();
        sumario = livro.getSumario();
        autor = new DetalheAutorDTOResponse(livro.getAutor());
    }

    @Override
    public String toString() {
        return "DetalheLivroDTOResponse{" +
                "titulo='" + titulo + '\'' +
                ", preco=" + preco +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", ISBN='" + ISBN + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", autor=" + autor +
                '}';
    }
}
