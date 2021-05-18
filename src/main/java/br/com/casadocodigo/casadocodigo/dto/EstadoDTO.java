package br.com.casadocodigo.casadocodigo.dto;

import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.model.Pais;
import br.com.casadocodigo.casadocodigo.repository.EstadoRepository;
import br.com.casadocodigo.casadocodigo.repository.PaisRepository;
import br.com.casadocodigo.casadocodigo.validacao.ExistsId;
import br.com.casadocodigo.casadocodigo.validacao.UniqueValue;
import br.com.casadocodigo.casadocodigo.validarErros.ErroAPI;
import br.com.casadocodigo.casadocodigo.validarErros.ErroDeValidacaoHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class EstadoDTO {

    @NotBlank
    //@UniqueValue(domainClass = Estado.class,fieldName = "nome", message = "O Estado informado já existe")
    private String nome;

    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id", message = "O identificador do país não foi informado")
    private Long idPais; //um país tem vários estados

    public Estado converter(EntityManager em, EstadoRepository estadoRepository){
        Pais pais = em.find(Pais.class, idPais);
        if(pais != null) {
            return new Estado(nome, pais);
        }

        return null;
    }

    public EstadoDTO(@NotBlank String nome, @NotNull Long idPais) {
        super();
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    @Override
    public String toString() {
        return "EstadoDTO{" +
                "nome='" + nome + '\'' +
                ", idPais=" + idPais +
                '}';
    }
}
