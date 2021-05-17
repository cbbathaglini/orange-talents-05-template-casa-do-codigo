package br.com.casadocodigo.casadocodigo.repository;

import br.com.casadocodigo.casadocodigo.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {
}
