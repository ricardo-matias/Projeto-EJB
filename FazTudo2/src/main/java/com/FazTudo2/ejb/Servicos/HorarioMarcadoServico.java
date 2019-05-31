/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.HorarioMarcado;
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

@Stateless(name = "ejb/HorarioMarcadoServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class HorarioMarcadoServico extends ServicoBean<HorarioMarcado> {

    @PostConstruct
    public void init() {
        super.setClasse(HorarioMarcado.class);
    }

    @Override
    public HorarioMarcado criar() {
        return new HorarioMarcado();
    }
     
    @Override
    public boolean existe(@NotNull HorarioMarcado entidade) {
        TypedQuery<HorarioMarcado> query
                = entityManager.createNamedQuery("Horario.porNivel_e_Comparecimento", HorarioMarcado.class);
        query.setParameter(1, entidade.getCliente().getNivel());
        query.setParameter(2, entidade.getComparecimento());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(SUPPORTS)
    public List<HorarioMarcado> horarioNivelComparecimento(@NotBlank int nivel, boolean comparecimento) {
        return super.consultarEntidades(new Object[] {nivel, comparecimento}, "Horario.porNivel_e_Comparecimento");
    } 
}
