/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Entidade.DonoEstabelecimento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author marcos
 */
@Stateless(name = "ejb/DonoEstabelecimentoServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class DonoEstabelecimentoServico extends Servico<DonoEstabelecimento> {

    @PostConstruct
    public void init() {
        super.setClasse(DonoEstabelecimento.class);
    }

    @Override
    public DonoEstabelecimento criar() {
        return new DonoEstabelecimento();
    }
    
    public void persistirDono(@Valid DonoEstabelecimento dono) {
        entityManager.persist(dono);
    }
    
    @Override
    public boolean existe(@NotNull DonoEstabelecimento entidade) {
        TypedQuery<DonoEstabelecimento> query
                = entityManager.createNamedQuery("DonoEstabelecimento.porCpf", DonoEstabelecimento.class);
        query.setParameter("cpf", entidade.getCpf());
        return !query.getResultList().isEmpty();
    }

    public DonoEstabelecimento atualizaDono(DonoEstabelecimento dono) {
        dono = entityManager.merge(dono);
        entityManager.flush();
        return dono;
    }    
    
    @TransactionAttribute(SUPPORTS)
    public List<DonoEstabelecimento> donoPorNome(@NotBlank String nome) {
        return super.consultarEntidades(new Object[] {nome}, "DonoEstabelecimento.porNome");
    }
    
    @TransactionAttribute(SUPPORTS)
    public DonoEstabelecimento donoPorCpf(@CPF String cpf) {
        return super.consultarEntidade(new Object[] {cpf}, "DonoEstabelecimento.porCpf");
    }    
}
