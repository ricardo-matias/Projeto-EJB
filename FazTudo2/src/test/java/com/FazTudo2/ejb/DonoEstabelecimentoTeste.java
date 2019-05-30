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
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
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
        dono.setCpf("740.707.044-00");
        assertTrue(donoServico.existe(dono));
    }

    @Test
    public void consultarDonoPorId() {
        assertNotNull(donoServico.consultarPorId(4L).getId());
        assertEquals("Zangado Teste", donoServico.consultarPorId(4L).getNome());
    }
    
    @Test
    public void atualizarDono() {
        DonoEstabelecimento dono = donoServico.donoPorNome("Ricardo Matias").get(0);
        dono.setNome("RICARDUA");
        dono = donoServico.atualizar(dono);
        assertEquals("RICARDUA", dono.getNome());
    }
    
    @Test(expected = EJBException.class)
    public void atualizarDonoinvalido() {
        DonoEstabelecimento dono = donoServico.donoPorNome("Natanael Lemos").get(0);
        dono.setCpf("222.111.444-98");//cpf invalido
        
        try {
            donoServico.atualizar(dono);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("{com.FazTudo2.ejb.Entidade.DonoEstabelecimento.cpf}"),
                                startsWith("CPF inválido!")));
            }
            
            throw ex;
        }
    }
    
    @Test
    public void consultarDonoPorNome() {
        DonoEstabelecimento dono = donoServico.donoPorNome("Zangado Teste").get(0);
        assertNotNull(dono);
        assertEquals("Zangado Teste", dono.getNome());
    }
    
    @Test
    public void persistirDono() {
        DonoEstabelecimento dono = donoServico.criar();
        dono.setNome("Teste EJB");
        dono.setCpf("735.392.180-33");
        dono.setLogin("Malicius5");
        dono.setSenha("Dieg!1998");
        donoServico.persistir(dono);
        assertNotNull(dono.getId());
        assertTrue(donoServico.existe(dono));
    } 
    
    @Test(expected = EJBException.class)
    public void consultarDonoCpfInvalido() {
        try {
            DonoEstabelecimento dono = donoServico.donoPorCpf("222.111.444-98");
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            throw ex;
        }
    }

}
