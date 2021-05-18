package br.com.casadocodigo.casadocodigo.repository;

import br.com.casadocodigo.casadocodigo.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Livro, Long> {
}
