package br.com.casadocodigo.casadocodigo.model;

import br.com.casadocodigo.casadocodigo.dao.AutorDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AutorTest {


    private static AutorDAO mockAutorDAO;
    private static Autor autor1;
    private static Autor autor2;
    private static Autor autor3;
    private static Autor autor4;
    private static Autor autor5;
    private static Autor autor6;
    private static Autor autor1_copy;

    @BeforeClass
    public static void init() {
        // set AutorDAO mock object
        mockAutorDAO = Mockito.mock(AutorDAO.class);

        // create an Autor object
        autor1 = new Autor( "Carine Bertagnolli Bathaglini", "cbbathaglini@gmail.com","descricao 1");

        // create another Autor object
        autor2 = new Autor( "Monica Geller", "monica_geller@gmail.com","descricao 2");

        // create another Autor object
        autor3 = new Autor( "Ross Geller", "ross_geller@gmail.com","descricao 3");

        // create another Autor object
        autor4 = new Autor("Chandler Bing", "chandler_bing@gmail.com","descricao 4");

        // create another Autor object
        autor5 = new Autor("Phoebe Buffay", "phoebe_buffay@gmail.com","descricao 5");

        autor6 = new Autor("Rachel Green", "rachel_green@gmail.com","descricao 6");

        // stubbing is done for test cases
        Mockito.when(mockAutorDAO.getAll()).thenReturn(Arrays.asList(autor1, autor2, autor3,autor4));

        //Mockito.when(mockAutorDAO.buscarPorId(1L)).thenReturn(autor1);
        Mockito.when(mockAutorDAO.salvarRetNome(autor1)).thenReturn(autor1.getNome());
        Mockito.when(mockAutorDAO.salvarRetNome(autor2)).thenReturn(autor2.getNome());
        Mockito.when(mockAutorDAO.salvarRetNome(autor3)).thenReturn(autor3.getNome());
        Mockito.when(mockAutorDAO.salvarRetNome(autor4)).thenReturn(autor4.getNome());

        Mockito.when(mockAutorDAO.salvarRetEmail(autor1)).thenReturn(autor1.getEmail());
        Mockito.when(mockAutorDAO.salvarRetEmail(autor2)).thenReturn(autor2.getEmail());
        Mockito.when(mockAutorDAO.salvarRetEmail(autor3)).thenReturn(autor3.getEmail());
        Mockito.when(mockAutorDAO.salvarRetEmail(autor4)).thenReturn(autor4.getEmail());

        Mockito.when(mockAutorDAO.salvar(autor1)).thenReturn(autor1);
        Mockito.when(mockAutorDAO.salvar(autor2)).thenReturn(autor2);
        Mockito.when(mockAutorDAO.salvar(autor3)).thenReturn(autor3);
        Mockito.when(mockAutorDAO.salvar(autor4)).thenReturn(autor4);
        Mockito.when(mockAutorDAO.salvar(autor6)).thenReturn(autor6);

        Mockito.when(mockAutorDAO.porNomeEEmail(autor1.getNome(),autor1.getEmail())).thenReturn(autor1);
        Mockito.when(mockAutorDAO.porNomeEEmail(autor2.getNome(),autor2.getEmail())).thenReturn(autor2);
        Mockito.when(mockAutorDAO.porNomeEEmail(autor3.getNome(),autor3.getEmail())).thenReturn(autor3);
        Mockito.when(mockAutorDAO.porNomeEEmail(autor4.getNome(),autor4.getEmail())).thenReturn(autor4);
        Mockito.when(mockAutorDAO.porNomeEEmail(autor6.getNome(),autor6.getEmail())).thenReturn(autor6);


        Mockito.when(mockAutorDAO.porNomeComecoLetra("R")).thenReturn(2);

        Mockito.when(mockAutorDAO.porNomeComecoLetra("A")).thenReturn(0);
        Mockito.when(mockAutorDAO.deletar(autor2)).thenReturn("Autor deletado!");


        autor1_copy = new Autor( "Carine Bertagnolli Bathaglini", "cbbathaglini@gmail.com","descricao 1");
        autor1.setNome("Gunter");
        Mockito.when(mockAutorDAO.alterar(autor1_copy)).thenReturn(autor1);


    }

    @Test
    public void alterarAutor() {
        Autor autorRet = mockAutorDAO.alterar(autor1_copy);
        assertEquals(autor1,autorRet);
    }



    @Test
    public void deletarAutor() {
        String deletado = mockAutorDAO.deletar(autor2);
        assertEquals("Autor deletado!",deletado);
    }

    @Test
    public void addAutorTest() {
        String nomeAutor = mockAutorDAO.salvarRetNome(autor2);
        assertNotNull(nomeAutor);
        assertEquals("Monica Geller", nomeAutor);
    }

    @Test
    public void procuraNomeEEmail() {
        Autor autorRet = mockAutorDAO.porNomeEEmail(autor3.getNome(),autor3.getEmail());
        assertNotNull(autorRet);
        assertEquals("Ross Geller", autorRet.getNome());
        assertEquals("ross_geller@gmail.com", autorRet.getEmail());
    }
    @Test
    public void qntNomeComecoR() {
        int qnt = mockAutorDAO.porNomeComecoLetra("R");
        assertEquals(2, qnt);
    }

    @Test
    public void qntNomeComecoA() {
        int qnt = mockAutorDAO.porNomeComecoLetra("A");
        assertEquals(0, qnt);
    }


    @Test
    public void selectNull() {
        Autor autorRet = mockAutorDAO.porNomeEEmail(autor5.getNome(),autor5.getEmail());
        assertNull(autorRet);
    }

    @Test
    public void getAllTestError() {
        List<Autor> listaAutores = mockAutorDAO.getAll();
        for (Autor a:listaAutores) {
            System.out.println(a.getEmail());
        }
        assertNotNull(listaAutores);
        assertEquals(4, listaAutores.size());
    }

}