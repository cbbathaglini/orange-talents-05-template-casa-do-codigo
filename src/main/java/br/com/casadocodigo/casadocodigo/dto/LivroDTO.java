package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.geo.Shape;

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
    private Long idCategoria;

    @NotNull
    private Long idAutor;


    public LivroDTO(@NotBlank String titulo,  @NotBlank @Size(max = 500) String resumo, String sumario, @NotBlank @Min(20) BigDecimal preco,
                    @NotBlank  @Min(100) Integer numeroPaginas, @NotBlank String ISBN,@NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.ISBN = ISBN;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro converter(){
        return  new Livro(this.titulo, this.resumo, this.sumario, this.preco, this.numeroPaginas, this.ISBN, this.dataPublicacao);
    }



}
