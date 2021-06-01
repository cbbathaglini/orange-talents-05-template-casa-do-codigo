package br.com.casadocodigo.casadocodigo.repository;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.model.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LivroRepository extends CrudRepository<Livro, Long> {
    Optional<Livro> findById(Long id);
    List<Livro> findByCategoria(Categoria categoria);

    @Query("SELECT avg(l.preco) FROM Livro l where l.preco >= :precoSugerido")
    BigDecimal findAvgPrecoSugerido(BigDecimal precoSugerido);

    @Query("SELECT avg(l.preco) FROM Livro l where l.dataPublicacao >= :data")
    Integer findAcimaDataX(LocalDate data);

    //@Query("SELECT l FROM Livro l  LIMIT 2")
    List<Livro> findTop3ByOrderByIdDesc();

    @Query("SELECT l FROM Livro l,Autor a where l.dataPublicacao >= :data and a = :autor and a = l.autor")
    List<Livro> findAcimaDataXAndAutorY(LocalDate data, Autor autor);

    @Query("SELECT sum(l.preco) FROM Livro l ")
    BigDecimal somaPrecoLivros();

    @Query("SELECT sum(l.preco) FROM Livro l,Autor a where a = :autor and a = l.autor ")
    BigDecimal somaPrecoLivrosDoAutor(Autor autor);
}
