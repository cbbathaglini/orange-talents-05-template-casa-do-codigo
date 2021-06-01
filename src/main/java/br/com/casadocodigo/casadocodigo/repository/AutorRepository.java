package br.com.casadocodigo.casadocodigo.repository;

import br.com.casadocodigo.casadocodigo.model.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {
    Optional<Autor> findByEmail(String email);

    @Query("SELECT a FROM Autor a WHERE a.nome = :nome AND a.email = :email ")
    Autor findByNomeEmail(String nome, String email);


    @Query("SELECT count(a) FROM Autor a WHERE a.nome like :nome  GROUP BY a.id")
    List<Autor> findByNomeLike(String nome);
}
