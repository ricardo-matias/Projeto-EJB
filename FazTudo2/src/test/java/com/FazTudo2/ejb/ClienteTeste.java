/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Servicos.ClienteServico;
import com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico;
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
public class ClienteTeste extends Teste {

    private ClienteServico clienteServico;

    @Before
    public void setUp() throws NamingException {
        clienteServico = (ClienteServico) container.getContext().lookup("java:global/classes/ejb/ClienteServico!com.FazTudo2.ejb.Servicos.ClienteServico");
    }

    @After
    public void tearDown() {
        clienteServico = null;
    }

    @Test
    public void existeCliente() {
        Cliente cliente = clienteServico.criar();
        cliente.setNome("Diego Santos");
        assertTrue(clienteServico.existe(cliente));
    }

    @Test
    public void consultarDonoPorId() {
        Cliente cliente = clienteServico.consultarPorId(2L);
        assertNotNull(cliente.getId());
        assertEquals("Diego Santos", cliente.getNome());
    }
    
    @Test
    public void consultarClientePorNome() {
        List<Cliente> clientes = clienteServico.clientePorNome("Diego Santos");
        assertEquals(1, clientes.size());
        assertNotNull(clientes.get(0).getId());
        assertEquals("Diego Santos", clientes.get(0).getNome());
    }
    
    @Test
    public void consultarClientePorNivel() {
        List<Cliente> clientes = clienteServico.clientePorNivel(1);
        assertEquals(2, clientes.size());
        
        for(Cliente cliente: clientes) {
            assertTrue(cliente.getNome().equals("Nicolas Melo") || cliente.getNome().equals("Cliente Falso"));
        }
    }

    @Test
    public void atualizarCliente() {
        List<Cliente> clientes = clienteServico.clientePorNome("Marcos Vinicius");
        assertEquals(1, clientes.size());
        Cliente cliente = clientes.get(0);
        cliente.setNome("Marcos Brasileiro");
        cliente.setNivel(5);
        cliente = clienteServico.atualizar(cliente);
        assertEquals("Marcos Brasileiro", cliente.getNome());
    }
    
    @Test
    public void persistirCliente() {
        Cliente cliente = clienteServico.criar();
        cliente.setNome("Teste EJB");
        cliente.setNivel(3);
        cliente.setLogin("Client99");
        cliente.setSenha("Dieg!1998");
        clienteServico.persistir(cliente);
        assertNotNull(cliente.getId());
        assertTrue(clienteServico.existe(cliente));
    } 
    
    @Test(expected = EJBException.class)
    public void atualizarClienteInvalido() {
        Cliente cliente = clienteServico.clientePorNome("Cliente Falso").get(0);
        cliente.setSenha("123");
        
        try {
            clienteServico.atualizar(cliente);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(startsWith("{com.FazTudo2.ejb.Entidade.Usuario.senha}"),
                                startsWith("A senha deve conter no minimo 6 caracteres")));
            }
            
            throw ex;
        }
    }

}
