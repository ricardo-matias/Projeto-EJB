/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.Endereco;
import com.FazTudo2.ejb.Entidade.Estabelecimento;
import com.FazTudo2.ejb.Servicos.ClienteServico;
import com.FazTudo2.ejb.Servicos.DonoEstabelecimentoServico;
import com.FazTudo2.ejb.Servicos.EstabelecimentoServico;
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
public class EstabelecimentoTeste extends Teste {

    private EstabelecimentoServico estabelecimentoServico;

    @Before
    public void setUp() throws NamingException {
        estabelecimentoServico = (EstabelecimentoServico) container.getContext().lookup("java:global/classes/ejb/EstabelecimentoServico!com.FazTudo2.ejb.Servicos.EstabelecimentoServico");
    }

    @After
    public void tearDown() {
        estabelecimentoServico = null;
    }

    @Test
    public void existeEstabelecimento() {
        Estabelecimento estabelecimento = estabelecimentoServico.criar();
        estabelecimento.setCnpj("14.970.257/0001-80");
        assertTrue(estabelecimentoServico.existe(estabelecimento));
    }

    @Test
    public void consultarEstabelecimentoPorId() {
        assertNotNull(estabelecimentoServico.consultarPorId(1L).getId());
        assertEquals("Barbearia do Malicius", estabelecimentoServico.consultarPorId(1L).getNome());
    }
    
    @Test
    public void consultarEstabelecimentoPorStatus() {
        List<Estabelecimento> estabelecimentos = estabelecimentoServico
                .estabelecimentoPorStatus("Disponível");
        assertEquals(6, estabelecimentos.size());
    }
    
    @Test
    public void consultarEstabelecimentoPorCNPJ() {
        Estabelecimento estabelecimento = estabelecimentoServico
                .estabelecimentoPorCNPJ("14.970.257/0001-80");
        assertEquals("Barbearia do Malicius", estabelecimento.getNome());
    }

    @Test
    public void atualizarEstabelecimento() {
        Estabelecimento estabelecimento = estabelecimentoServico
                .estabelecimentoPorCNPJ("02.722.823/0001-56");
        assertEquals("Bar do Guilherme", estabelecimento.getNome());
        estabelecimento.setNome("Bar da Hyper20");
        estabelecimento = estabelecimentoServico.atualizar(estabelecimento);
        assertEquals("Bar da Hyper20", estabelecimento.getNome());
    }
    
    @Test
    public void persistirEstabelecimento() {
        Estabelecimento estabelecimento = criarEstabelecimento();
        estabelecimentoServico.persistir(estabelecimento);
        assertNotNull(estabelecimento.getId());
        assertTrue(estabelecimentoServico.existe(estabelecimento));
    } 
    
    private Estabelecimento criarEstabelecimento() { 
        Estabelecimento estabelecimento = estabelecimentoServico.criar();
        estabelecimento.setNome("Teste EJB");
        estabelecimento.setCnpj("87.003.464/0001-30");
        estabelecimento.setStatus("Indisponível");
        
        Endereco endereco = new Endereco();
        endereco.setNumero(330);
        endereco.setBairro("Várzea");
        endereco.setRua("Rua da Lama");
        endereco.setCidade("Recife");
        endereco.setEstado("Pernambuco");
        endereco.setComplemento("A");
        endereco.setCep("57048355");
        
        estabelecimento.setEndereco(endereco);
        return estabelecimento;
    }

}
