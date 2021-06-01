package br.com.casadocodigo.casadocodigo.model;

import br.com.casadocodigo.casadocodigo.dao.LivroDAO;
import org.apache.tomcat.jni.Local;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class LivroTest {


    private static LivroDAO mockLivroDAO;
    private static Livro livro1;
    private static Livro livro2;
    private static Livro livro3;
    private static Livro livro4;
    private static Autor autor1;
    private static Autor autor2;
    private static Autor autor3;
    private static Autor autor4;
    private static Autor autor5;
    private static Categoria categoria1;
    private static Categoria categoria2;
    private static Categoria categoria3;
    private static BigDecimal preco1 ;
    private static BigDecimal preco2 ;
    private static BigDecimal preco3 ;
    private static BigDecimal mediaFinal ;
    private static LocalDate date1;
    private static LocalDate date2;
    private static LocalDate date3;
    private static LocalDate date4;
    private static BigDecimal somaLivros ;

    @BeforeClass
    public static void init() {
        // set LivroDAO mock object
        mockLivroDAO = Mockito.mock(LivroDAO.class);


        autor1 = new Autor("Monica Geller", "monica_geller@gmail.com", "descricao 2");
        autor2 = new Autor("Ross Geller", "ross_geller@gmail.com", "descricao 3");
        autor3 = new Autor("Chandler Bing", "chandler_bing@gmail.com", "descricao 4");
        autor4 = new Autor("Phoebe Buffay", "phoebe_buffay@gmail.com", "descricao 5");
        autor5 = new Autor("Rachel Green", "rachel_green@gmail.com", "descricao 6");

        categoria1 = new Categoria("Romance");
        categoria2 = new Categoria("Piadas");
        categoria3 = new Categoria("Paleontologia");

        preco1 = new BigDecimal(30.67);
        preco2 = new BigDecimal(120.30);
        preco3 = new BigDecimal(43.89);

        date1 = LocalDate.of(2022, Month.JANUARY, 8);
        date2 = LocalDate.of(2022, Month.OCTOBER, 10);
        date3 = LocalDate.of(2022, Month.SEPTEMBER, 8);

        List<Livro> listaLivros = new ArrayList<>();
        livro1 = new Livro("Livro sobre Monica Geller", "resumo resumo", "sumario", preco1, 150, "ADRF8765990", date1, autor1, categoria1);
        listaLivros.add(livro1);
        livro2 = new Livro("Livro sobre Chandler Bing", "resumo resumo", "sumario", preco2, 720, "ADRF876575", date2, autor3, categoria2);
        listaLivros.add(livro2);
        livro3 = new Livro("Livro sobre Ross Geller", "resumo resumo", "sumario", preco3, 200, "ADAS8765990", date3, autor2, categoria3);
        listaLivros.add(livro3);
        livro4 = new Livro("Livro sobre Monica Geller", "resumo resumo", "sumario", preco2, 130, "ADAS8765990", date1, autor1, categoria1);
        listaLivros.add(livro4);

        // stubbing is done for test cases
        Mockito.when(mockLivroDAO.buscarPorCategoria(categoria1)).thenReturn(Arrays.asList(livro1, livro4));

        Mockito.when(mockLivroDAO.salvar(livro1)).thenReturn(livro1);
        Mockito.when(mockLivroDAO.salvar(livro2)).thenReturn(livro2);
        Mockito.when(mockLivroDAO.salvar(livro3)).thenReturn(livro3);
        Mockito.when(mockLivroDAO.salvar(livro4)).thenReturn(livro4);

        BigDecimal precoAcimaDe = new BigDecimal(43.89);
        BigDecimal soma = preco2.add(preco3);
        mediaFinal = soma.divide(new BigDecimal(2));
        //System.out.println("Soma: "+ soma);
        //System.out.println("Media: " + mediaFinal);
        Mockito.when(mockLivroDAO.avgPrecoSugerido(precoAcimaDe)).thenReturn(mediaFinal);
        somaLivros = new BigDecimal(0.0);
        for (Livro l:listaLivros) {
            somaLivros.add(l.getPreco());
        }

        Mockito.when(mockLivroDAO.somaPrecoLivros()).thenReturn(somaLivros);

        Mockito.when(mockLivroDAO.listarLivrosComLimit()).thenReturn(Arrays.asList(livro1, livro2,livro3));


        date4 = LocalDate.of(2022, Month.NOVEMBER, 16);
        Mockito.when(mockLivroDAO.acimaDataX(date1)).thenReturn(3);
        Mockito.when(mockLivroDAO.acimaDataX(date3)).thenReturn(2);
        Mockito.when(mockLivroDAO.acimaDataX(date2)).thenReturn(1);
        Mockito.when(mockLivroDAO.acimaDataX(date4)).thenReturn(0);

        Mockito.when(mockLivroDAO.acimaDataXAndAutorY(date1,autor1)).thenReturn(Arrays.asList(livro1, livro4));

        Mockito.when(mockLivroDAO.somaPrecoLivrosDoAutor(autor2)).thenReturn(preco3);

        Mockito.when(mockLivroDAO.deletar(livro1)).thenReturn("Livro deletado!");

        Mockito.when(mockLivroDAO.listarLivros()).thenReturn(listaLivros);
    }

    @Test
    public void deletarLivro() {
        String deletado = mockLivroDAO.deletar(livro1);
        assertEquals("Livro deletado!",deletado);
    }

    @Test
    public void acimaDataXAndAutorY() {
        List<Livro> listaLivros = mockLivroDAO.acimaDataXAndAutorY(date1,autor1);
        assertEquals(livro1, listaLivros.get(0));
        assertEquals(livro4, listaLivros.get(1));
    }

    @Test
    public void somaPrecoLivros() {
        BigDecimal somaPrecos = mockLivroDAO.somaPrecoLivros();
        assertEquals(somaLivros, somaPrecos);
    }

    @Test
    public void somaPrecoLivrosDoAutor2() {
        BigDecimal somaPrecos = mockLivroDAO.somaPrecoLivrosDoAutor(autor2);
        assertEquals(preco3, somaPrecos);
    }

    @Test
    public void buscarCategoria() {
        List<Livro> listaLivros = mockLivroDAO.buscarPorCategoria(categoria1);
        assertEquals(2, listaLivros.size());
    }


    @Test
    public void mediaPrecoSugerido() {
        BigDecimal media = mockLivroDAO.avgPrecoSugerido(preco3);
        assertEquals(mediaFinal, media);
    }

    @Test
    public void listandoLivrosLimit() {
        List<Livro> livros  = mockLivroDAO.listarLivrosComLimit();
        assertEquals(3, livros.size());
    }

    @Test
    public void listandoLivros() {
        List<Livro> livros  = mockLivroDAO.listarLivros();
        assertEquals(livro1, livros.get(0));
        assertEquals(livro2, livros.get(1));
        assertEquals(livro3, livros.get(2));
        assertEquals(livro4, livros.get(3));

    }


    @Test
    public void qntLivrosPublicadosAcimaDaData1() {
        int qntLivrosPublicados  = mockLivroDAO.acimaDataX(date1);
        assertEquals(3, qntLivrosPublicados);
    }
    @Test
    public void qntLivrosPublicadosAcimaDaData3() {
        int qntLivrosPublicados  = mockLivroDAO.acimaDataX(date3);
        assertEquals(2, qntLivrosPublicados);
    }
    @Test
    public void qntLivrosPublicadosAcimaDaData2() {
        int qntLivrosPublicados  = mockLivroDAO.acimaDataX(date2);
        assertEquals(1, qntLivrosPublicados);
    }
    @Test
    public void qntLivrosPublicadosAcimaDaData4() {
        int qntLivrosPublicados  = mockLivroDAO.acimaDataX(date4);
        assertEquals(0, qntLivrosPublicados);
    }



}