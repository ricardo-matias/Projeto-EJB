/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

/**
 *
 * @author diego
 */
@Entity
@Table(name="tb_horarios_marcados")
@Access(AccessType.FIELD)
@NamedNativeQueries (
        @NamedNativeQuery (
                name = "Horario.porNivel_e_Comparecimento",
                query = "SELECT DISTINCT " +
                        "hr.id, hr.comparecimento, hr.data, hr.id_cliente, hr.id_servico " +
                        "FROM tb_horarios_marcados hr " +
                        "INNER JOIN tb_cliente c ON c.id_usuario = hr.id_cliente " +
                        "WHERE c.nivel <= ?1 AND hr.comparecimento = ?2",
                resultClass = HorarioMarcado.class
        )
)
public class HorarioMarcado extends Entidade implements Serializable{
    
    public HorarioMarcado(){}
    
    //@Future
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private Date data;
    
    @Column(name = "comparecimento")
    private boolean comparecimento;    
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_servico", referencedColumnName = "id")
    private Servico servico;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_usuario")
    private Cliente cliente;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean getComparecimento() {
        return comparecimento;
    }

    public void setComparecimento(boolean comparecimento) {
        this.comparecimento = comparecimento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
