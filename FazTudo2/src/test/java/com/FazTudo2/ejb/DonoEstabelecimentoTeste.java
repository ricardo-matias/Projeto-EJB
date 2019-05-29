/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Entidade.DonoEstabelecimento;
import com.FazTudo2.ejb.Entidade.Servico;
import com.FazTudo2.ejb.Servicos.CategoriaServico;
import com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico;
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
public class DonoEstabelecimentoTeste extends Teste {

    private DonoEstabelecimentoServico donoServico;

    @Before
    public void setUp() throws NamingException {
        donoServico = (DonoEstabelecimentoServico) container.getContext().lookup("java:global/classes/ejb/DonoEstabelecimentoServico!com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico");
    }

    @After
    public void tearDown() {
        donoServico = null;
    }
    
    @Test
    public void existeDono() { 
        DonoEstabelecimento dono = donoServico.criar();
        dono.setCpf("47771662013");
        assertTrue(donoServico.existe(dono));
    }

    @Test
    public void consultarDonoPorId() {
        assertNotNull(donoServico.consultarDonoPorId(1L).getId());
        assertEquals("Ricardo Matias", donoServico.consultarDonoPorId(1L).getNome());
    }
    
    @Test
    public void atualizarDono() {
        DonoEstabelecimento dono = donoServico.donoPorNome("Ricardo Matias").get(0);
        dono.setNome("RICARDU");
        System.err.println("======CPF:  " + dono.getCpf());
        dono = donoServico.atualizar(dono);
        assertEquals("RICARDU", dono.getNome());
    }
    
    @Test
    public void consultarDonoPorNome() {
        DonoEstabelecimento dono = donoServico.donoPorNome("Ricardo Matias").get(0);
        assertNotNull(dono);
        assertEquals("Ricardo Matias", dono.getNome());
    }
    
    @Test
    public void consultarDonoPorId2() {
        //DonoEstabelecimento dono = donoServico.donoPorId(1L).get(0);
        DonoEstabelecimento dono = donoServico.consultarPorIdDONO(1L);
        System.out.println("NOME: " + dono.getNome());
        assertNotNull(dono);
        assertEquals("Ricardo Matias", dono.getNome());
    }
    
//    @Test
//    public void persistirDono() {
//        DonoEstabelecimento dono = donoServico.criar();
//        dono.setNome("Teste EJB");
//        dono.setCpf("08704492498");
//        dono.setLogin("Malicius5");
//        dono.setSenha("Dieg!1998");
//        donoServico.persistir(dono);
//        assertNotNull(dono.getId());
//        assertTrue(donoServico.existe(dono));
//    } 
    /*
    @Test
    public void atualizarCategoria() { 
        Categoria categoria = categoriaServico.consultarPorId(new Long(1));
        logger.info(categoria.getNome());
        categoria.setNome("Estetica");
        categoria = categoriaServico.atualizar(categoria);
        assertEquals("Estetica", categoria.getNome());
    }*/

}
