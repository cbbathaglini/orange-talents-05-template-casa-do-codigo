package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Cliente;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.model.Pais;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Cliente.class, fieldName = "email", message = "O email do cliente informado já existe")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento", message = "O documento informado já existe")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    private Long idPais;

    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String CEP;


    public ClienteDTO(@NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
                      @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade,
                      @NotNull Long idPais, Long idEstado,  @NotBlank String telefone,  @NotBlank String CEP) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.CEP = CEP;
    }

    public Cliente converter(EntityManager em){
        Pais pais = em.find(Pais.class, idPais);
        Estado estado = em.find(Estado.class, idEstado);

        return new Cliente(email, nome, sobrenome,documento,endereco,complemento,cidade,pais, estado,telefone,CEP);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCEP() {
        return CEP;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", CEP='" + CEP + '\'' +
                '}';
    }
}
