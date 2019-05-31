/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Cliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;

@Stateless(name = "ejb/ClienteServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class ClienteServico extends ServicoBean<Cliente> {

    @PostConstruct
    public void init() {
        super.setClasse(Cliente.class);
    }

    @Override
    public Cliente criar() {
        return new Cliente();
    }
     
    @Override
    public boolean existe(@NotNull Cliente entidade) {
        TypedQuery<Cliente> query
                = entityManager.createNamedQuery("Cliente.porNome", Cliente.class);
        query.setParameter(1, entidade.getNome());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(SUPPORTS)
    public List<Cliente> clientePorNome(@NotBlank String nome) {
        return super.consultarEntidades(new Object[] {nome}, "Cliente.porNome");
    }
    
    @TransactionAttribute(SUPPORTS)
    public List<Cliente> clientePorNivel(@NotNull int nivel) { // ----REVISAR---- parametros
        return super.consultarEntidades(new Object[] {nivel}, "Cliente.porNivel");
    }    
}
