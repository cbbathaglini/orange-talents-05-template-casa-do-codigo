package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;
import org.springframework.util.Assert;

import javax.validation.constraints.*;

public class AutorDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "O email informado já existe")
    private String email;

    @NotBlank @Size(max = 400)
    private String descricao;


    public AutorDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank  @Size(max = 400) String descricao) {
        Assert.hasLength(nome, "É obrigatório informar o nome");
        Assert.hasLength(email, "É obrigatório informar o email");
        Assert.hasLength(descricao, "É obrigatório informar a descrição");
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converter() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "AutorDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

}
