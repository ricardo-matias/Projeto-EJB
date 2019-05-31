/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.HorarioMarcado;
import com.FazTudo2.ejb.Entidade.Servico;
import com.FazTudo2.ejb.Servicos.ClienteServico;
import com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico;
import com.FazTudo2.ejb.Servicos.HorarioMarcadoServico;
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
public class HorarioMarcadoTeste extends Teste {

    private HorarioMarcadoServico horarioServico;

    @Before
    public void setUp() throws NamingException {
        horarioServico = (HorarioMarcadoServico) container.getContext().lookup("java:global/classes/ejb/HorarioMarcadoServico!com.FazTudo2.ejb.Servicos.HorarioMarcadoServico");
    }

    @After
    public void tearDown() {
        horarioServico = null;
    }

    @Test
    public void existeHorario() {
        HorarioMarcado horario = horarioServico.criar();
        Cliente cliente = new Cliente();
        cliente.setNivel(5);
        horario.setCliente(cliente);
        horario.setComparecimento(false);
        assertTrue(horarioServico.existe(horario));
    }

    @Test
    public void consultarHorarioPorId() {
        assertNotNull(horarioServico.consultarPorId(1L).getId());
        assertEquals(true, horarioServico.consultarPorId(1L).getComparecimento());
    }
 
    @Test
    public void aualizarHorario() {
        HorarioMarcado horario = horarioServico.consultarPorId(2L);
        assertEquals(false, horario.getComparecimento());
        horario.setComparecimento(true);
        horario = horarioServico.atualizar(horario);
        assertEquals(true, horario.getComparecimento());
    }
    /*
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
    }*/

}
