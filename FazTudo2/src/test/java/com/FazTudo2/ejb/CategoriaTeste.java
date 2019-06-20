/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Servicos.CategoriaServico;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ricardo
 */
public class CategoriaTeste extends Teste {

    private CategoriaServico categoriaServico;

    @Before
    public void setUp() throws NamingException {
        categoriaServico = (CategoriaServico) container.getContext().lookup("java:global/classes/ejb/CategoriaServico!com.FazTudo2.ejb.Servicos.CategoriaServico");
    }

    @After
    public void tearDown() {
        categoriaServico = null;
    }
    
    @Test
    public void existeCategoria() { 
        Categoria categoria = categoriaServico.criar();
        categoria.setNome("Bebidas");
        assertTrue(categoriaServico.existe(categoria));
    }

    @Test
    public void consultarCategoriaPorId() {
        Categoria categoria = categoriaServico.consultarPorId(2L);
        assertNotNull(categoria.getId());
        assertEquals("Esporte", categoria.getNome());
    }
    
    @Test
    public void consultarCategoriaPorNome() {
        Categoria categoria = categoriaServico.categoriaPorNome("Esporte");
        assertNotNull(categoria.getId());
        assertEquals("Esporte", categoria.getNome());
    }
    
    @Test
    public void persistirCategoria() {
        Categoria categoria = categoriaServico.criar();
        categoria.setNome("Teste EJB");
        categoriaServico.persistir(categoria);
        assertNotNull(categoria.getId());
        //assertTrue(categoriaServico.existe(categoria));
    } 
    
    @Test
    public void atualizarCategoria() { 
        Categoria categoria = categoriaServico.consultarPorId(new Long(1));
        logger.info(categoria.getNome());
        categoria.setNome("Estetica");
        categoria = categoriaServico.atualizar(categoria);
        assertEquals("Estetica", categoria.getNome());
    }

}
