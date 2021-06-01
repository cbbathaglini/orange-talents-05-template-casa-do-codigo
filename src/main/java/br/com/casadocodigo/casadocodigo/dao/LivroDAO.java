package br.com.casadocodigo.casadocodigo.dao;

import br.com.casadocodigo.casadocodigo.model.Autor;
import br.com.casadocodigo.casadocodigo.model.Categoria;
import br.com.casadocodigo.casadocodigo.model.Livro;
import br.com.casadocodigo.casadocodigo.repository.AutorRepository;
import br.com.casadocodigo.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LivroDAO {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> buscarPorCategoria(Categoria categoria){
        return livroRepository.findByCategoria(categoria);
    }

    public Livro salvar(Livro livro) {
        livroRepository.save(livro);
        return  livro;
    }

    public String deletar(Livro livro) {
        livroRepository.delete(livro);
        return "Livro deletado!";
    }



    public BigDecimal avgPrecoSugerido(BigDecimal precoSugerido) {
        return livroRepository.findAvgPrecoSugerido(precoSugerido);
    }

    public List<Livro> listarLivrosComLimit() {
        return livroRepository.findTop3ByOrderByIdDesc();
    }

    public List<Livro> listarLivros() {
        return (List<Livro>) livroRepository.findAll();
    }

    public Integer acimaDataX(LocalDate dataInformada) {
        return livroRepository.findAcimaDataX(dataInformada);
    }

    public List<Livro> acimaDataXAndAutorY(LocalDate data,Autor autor){
        return livroRepository.findAcimaDataXAndAutorY(data,autor);
    }

    public BigDecimal somaPrecoLivros(){
        BigDecimal soma =  new BigDecimal(String.valueOf(livroRepository.somaPrecoLivros()));
        return soma;
    }

    public BigDecimal somaPrecoLivrosDoAutor(Autor autor){
        BigDecimal soma =  new BigDecimal(String.valueOf(livroRepository.somaPrecoLivrosDoAutor(autor)));
        return soma;
    }
}
