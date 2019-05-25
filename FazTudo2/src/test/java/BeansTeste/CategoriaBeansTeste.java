/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeansTeste;

import com.FazTudo2.ejb.Beans.CategoriaBean;
import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Servicos.CategoriaServico;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.naming.NamingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author davi
 */
public class CategoriaBeansTeste extends Teste {
    
    private CategoriaBean categoriaBean;
    
     @Before
    public void setUp() throws NamingException {
        categoriaBean = (CategoriaBean) container.getContext().lookup("java:global/classes/CategoriaBean!com.FazTudo2.ejb.Beans.CategoriaBean");
    }
    
    @After
    public void tearDown() {
        categoriaBean = null;
    }
    
    
    @Test
    public void categoriaId() { 
        Categoria categoria = categoriaBean.consultarCategoriaPorId(1L);
        assertNotNull(categoria);
    }
    
    @Test
    public void existeCategoria(){
        Categoria categoria = categoriaBean.criarCategoria();
        categoria.setNome("Beleza");
        assertTrue(categoriaBean.existeCategoria(categoria));
    }
    
    @Test
    public void aluguelPorPessoa(){
      List<Categoria> categorias = categoriaBean.categoriaPorNome();
        assertTrue(!categorias.isEmpty());
    }
    
}
