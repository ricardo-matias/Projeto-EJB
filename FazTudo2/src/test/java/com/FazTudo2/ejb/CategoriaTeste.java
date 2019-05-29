/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Entidade.Servico;
import com.FazTudo2.ejb.Servicos.CategoriaServico;
import java.math.BigDecimal;
import java.util.List;
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
        assertNotNull(categoriaServico.consultarCategoriaPorId(2L).getId());
        assertEquals("Esporte", categoriaServico.consultarCategoriaPorId(2L).getNome());
    }
    
    @Test
    public void consultarCategoriaPorNome() {
        Categoria categoria = categoriaServico.categoriaPorNome("Esporte").get(0);
        assertNotNull(categoria);
        assertEquals("Esporte", categoria.getNome());
    }
    
    @Test
    public void persistirCategoria() {
        Categoria categoria = categoriaServico.criar();
        categoria.setNome("Teste EJB");
        categoriaServico.persistir(categoria);
        assertNotNull(categoria.getId());
        assertTrue(categoriaServico.existe(categoria));
    } 
    
    @Test
    public void atualizarCategoria() { 
        Categoria categoria = categoriaServico.consultarPorId(new Long(1));
        logger.info(categoria.getNome());
        categoria.setNome("Estetica");
        categoria = categoriaServico.atualizar(categoria);
        assertEquals("Estetica", categoria.getNome());
    }
    

    /*@Test
    public void consultarItensPorTitulo() {
        assertEquals(1, itemServico.getItensPorTitulo("boss DM-2").size());
    }*/

//    @Test
//    public void adicionarOferta() {
//        Item item = itemServico.consultarPorId(new Long(6));
//        assertNotNull(item);
//        Comprador comprador = compradorServico.consultarPorId(new Long(2));
//        Oferta oferta = item.criarOferta();
//        oferta.setComprador(comprador);
//        oferta.setValor(BigDecimal.valueOf(500.00));
//        assertFalse(item.adicionar(oferta)); //Oferta menor que valor mínimo
//        oferta.setValor(BigDecimal.valueOf(750.00));
//        assertTrue(item.adicionar(oferta)); //Oferta igual ao valor mínimo
//        comprador = compradorServico.consultarPorId(new Long(1));
//        oferta = item.criarOferta();
//        oferta.setComprador(comprador);
//        oferta.setValor(BigDecimal.valueOf(745.00));
//        assertFalse(item.adicionar(oferta)); //Oferta menor que oferta maior oferta
//        oferta.setValor(BigDecimal.valueOf(770.00));
//        assertTrue(item.adicionar(oferta)); //Oferta maior que oferta atual)
//    }

}
