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
import com.FazTudo2.ejb.Servicos.HorarioMarcadoServico;
import com.FazTudo2.ejb.Servicos.ServicoServico;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private ClienteServico clienteServico;
    private ServicoServico servicoServico;

    @Before
    public void setUp() throws NamingException {
        horarioServico = (HorarioMarcadoServico) container.getContext().lookup("java:global/classes/ejb/HorarioMarcadoServico!com.FazTudo2.ejb.Servicos.HorarioMarcadoServico");
        clienteServico = (ClienteServico) container.getContext().lookup("java:global/classes/ejb/ClienteServico!com.FazTudo2.ejb.Servicos.ClienteServico");
        servicoServico = (ServicoServico) container.getContext().lookup("java:global/classes/ejb/ServicoServico!com.FazTudo2.ejb.Servicos.ServicoServico");
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
        HorarioMarcado horario = horarioServico.consultarPorId(1L);
        assertNotNull(horario.getId());
        assertEquals(true, horario.getComparecimento());
    }

    @Test
    public void consultarHorarioPorData() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date d = formato.parse("22/06/2020");
        HorarioMarcado horario = horarioServico.horarioPorData(d).get(0);
        assertNotNull(horario.getId());
        assertEquals("Nicolas Melo", horario.getCliente().getNome());
    }
 
    @Test
    public void aualizarHorario() {
        HorarioMarcado horario = horarioServico.consultarPorId(4L);
        assertEquals(false, horario.getComparecimento());
        horario.setComparecimento(true);
        horario = horarioServico.atualizar(horario);
        assertEquals(true, horario.getComparecimento());
    }
    
    @Test
    public void persistirHorario() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020, Calendar.JANUARY, 15);
        HorarioMarcado horario = horarioServico.criar();
        horario.setComparecimento(true);
        List<Cliente> clientes = clienteServico.clientePorNome("Diego Santos");
        assertEquals(1, clientes.size());
        Cliente cliente = clientes.get(0);
        horario.setCliente(cliente);
        List<Servico> servicos = servicoServico.servicoPorNome("Lava Jato");
        Servico servico = servicos.get(0);
        horario.setServico(servico);
        horario.setData(calendar.getTime());
        horarioServico.persistir(horario);
        assertNotNull(horario.getId());
        assertTrue(horarioServico.existe(horario));
    } 
    
    @Test(expected = EJBException.class)
    public void atualizarHorarioInvalido() {
        HorarioMarcado horario = horarioServico.consultarPorId(1L);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, Calendar.JANUARY, 15);
        horario.setData(calendar.getTime());
        try {
            horarioServico.atualizar(horario);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("{com.FazTudo2.ejb.Entidade.HorarioMarcado.data}"),
                                startsWith("A data deve estar no futuro")));
            }
            
            throw ex;
        }
    }

}
