/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.Servico;
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

@Stateless(name = "ejb/ServicoServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class ServicoServico extends ServicoBean<Servico> {

    @PostConstruct
    public void init() {
        super.setClasse(Servico.class);
    }

    @Override
    public Servico criar() {
        return new Servico();
    }
     
    @Override
    public boolean existe(@NotNull Servico entidade) {
        TypedQuery<Cliente> query
                = entityManager.createNamedQuery("Servico.porNome", Cliente.class);
        query.setParameter(1, entidade.getNome());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(SUPPORTS)
    public List<Servico> servicoPorNome(@NotBlank String nome) {
        return super.consultarEntidades(new Object[] {nome}, "Servico.porNome");
    } 
}
