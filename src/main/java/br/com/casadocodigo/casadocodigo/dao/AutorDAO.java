package br.com.casadocodigo.casadocodigo.dao;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AutorDAO {

    @Autowired
    private AutorRepository autorRepository;


    public Autor buscarPorId(Long id) {
        Optional<Autor> autorOptional = autorRepository.findById(id);

        if(autorOptional.isPresent()) {
            return autorOptional.get();
        }

        return null;
    }

    public Autor porNomeEEmail(String nome, String email) {
        System.out.println("nome: " + nome);
        System.out.println("email: " + email);
        return autorRepository.findByNomeEmail(nome,email);
    }

    public String salvarRetNome(Autor autor) {
        autorRepository.save(autor);
        return autor.getNome();
    }

    public Integer porNomeComecoLetra(String comecoNome){
        List<Autor> autores =  autorRepository.findByNomeLike("%"+comecoNome+"%");
        for (Autor a :autores) {
            System.out.println(a.getEmail());
        }
        return autores.size();
    }

    public Autor salvar(Autor autor) {
        autorRepository.save(autor);
        return  autor;
    }
    public String deletar(Autor autor) {
        autorRepository.delete(autor);
        return  "Autor deletado!";
    }

    public Autor alterar(Autor autor) {
        autorRepository.save(autor);
        return  autor;
    }

    public String salvarRetEmail(Autor autor) {
        autorRepository.save(autor);
        return autor.getEmail();
    }

    public List<Autor> getAll() {
        return (List<Autor>) autorRepository.findAll();
    }


}
