/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.Estabelecimento;
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

@Stateless(name = "ejb/EstabelecimentoServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class EstabelecimentoServico extends ServicoBean<Estabelecimento> {

    @PostConstruct
    public void init() {
        super.setClasse(Estabelecimento.class);
    }

    @Override
    public Estabelecimento criar() {
        return new Estabelecimento();
    }
     
    @Override
    public boolean existe(@NotNull Estabelecimento entidade) {
        TypedQuery<Estabelecimento> query
                = entityManager.createNamedQuery("Estabelecimento.porCNPJ", Estabelecimento.class);
        query.setParameter(1, entidade.getCnpj());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(SUPPORTS)
    public Estabelecimento estabelecimentoPorCNPJ(@NotBlank String cnpj) {
        return super.consultarEntidade(new Object[] {cnpj}, "Estabelecimento.porCNPJ");
    }
    
    @TransactionAttribute(SUPPORTS)
    public List<Estabelecimento> estabelecimentoPorStatus(@NotBlank String status) { // ----REVISAR---- parametros
        return super.consultarEntidades(new Object[] {status}, "Estabelecimento.porStatus");
    }    
    
    // estabelecimento por categoria
}
