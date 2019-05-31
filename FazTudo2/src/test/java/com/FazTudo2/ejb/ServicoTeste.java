/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.Servico;
import com.FazTudo2.ejb.Servicos.ClienteServico;
import com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico;
import com.FazTudo2.ejb.Servicos.ServicoServico;
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
public class ServicoTeste extends Teste {

    private ServicoServico servicoServico;

    @Before
    public void setUp() throws NamingException {
        servicoServico = (ServicoServico) container.getContext().lookup("java:global/classes/ejb/ServicoServico!com.FazTudo2.ejb.Servicos.ServicoServico");
    }

    @After
    public void tearDown() {
        servicoServico = null;
    }

    @Test
    public void existeServico() {
        Servico servico = servicoServico.criar();
        servico.setNome("Costura de roupas");
        assertTrue(servicoServico.existe(servico));
    }

    @Test
    public void consultarServicoPorId() {
        Servico servico = servicoServico.consultarPorId(1L);
        assertNotNull(servico.getId());
        assertEquals("Costura de roupas", servico.getNome());
    }
    
    @Test
    public void consultarServicoPorNome() { // trocar pra contains
        List<Servico> servicos = servicoServico.servicoPorNome("Costura de roupas");
        assertEquals(1, servicos.size());
        assertNotNull(servicos.get(0).getId());
        assertEquals("Costura de roupas", servicos.get(0).getNome());
    }
    
//    @Test
//    public void consultarServicoPorCategoria() {
//
//    }

    @Test
    public void atualizarServico() {
        Servico servico = servicoServico.consultarPorId(3L);
        assertEquals("Venda de roupas", servico.getNome());
        servico.setNome("Comércio de vestimentas");
        servico = servicoServico.atualizar(servico);
        assertEquals("Comércio de vestimentas", servico.getNome());
    }
    
    @Test
    public void persistirServico() {
        Servico servico = servicoServico.criar();
        servico.setNome("Teste EJB");
        servico.setDescricao("Servico para teste de persistência");
        servicoServico.persistir(servico);
        assertNotNull(servico.getId());
        assertTrue(servicoServico.existe(servico));
    } 
    
    @Test(expected = EJBException.class)
    public void atualizarServicoInvalido() {
        Servico servico = servicoServico.consultarPorId(5L);
        servico.setDescricao("Venda de bebidas babaca");
        
        try {
            servicoServico.atualizar(servico);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("{com.FazTudo2.ejb.Entidade.Servico.descricao}"),
                                startsWith("A descrição não pode conter palavrões")));
            }
            
            throw ex;
        }
    }

}
