package br.com.casadocodigo.casadocodigo.repository;

import br.com.casadocodigo.casadocodigo.model.Estado;
import br.com.casadocodigo.casadocodigo.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e, Pais p WHERE e.nome = :nome AND p.id = :idPais "
            + "AND e.pais = p " )
    List<Estado> findEstadoByNomeNoPais(String nome, Long idPais);


    //dado o país existe algum Estado?
    @Query("SELECT e FROM Estado e, Pais p WHERE p.id = :idPais "
            + "AND e.pais = p " )
    List<Estado> findEstadosNoPais(Long idPais);
}
