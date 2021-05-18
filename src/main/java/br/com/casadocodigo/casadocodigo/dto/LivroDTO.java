package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.validacao.ExistsId;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.geo.Shape;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class LivroDTO {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "O título informado já existe")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "ISBN", message = "O ISBN informado já existe")
    private String ISBN; //International Standard Book Number

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "O identificador da categoria não foi informado")
    private Long idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id", message = "O identificador do autor não foi informado")
    private Long idAutor;


    public LivroDTO(@NotBlank String titulo,
                            @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                            @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas,
                            @NotBlank String ISBN, @NotNull Long idCategoria,
                            @NotNull Long idAutor) {
        super();
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.ISBN = ISBN;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro converter(EntityManager em) {
        @NotNull Autor autor = em.find(Autor.class, idAutor);
        @NotNull Categoria categoria = em.find(Categoria.class, idCategoria);

        Assert.state(autor!=null,"Você esta querendo cadastrar um livro para um autor que nao existe no banco "+idAutor);
        Assert.state(categoria!=null,"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco "+idCategoria);

        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, ISBN, dataPublicacao, autor, categoria);
    }



}
