/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.QueryHint;
import javax.persistence.Table;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "id_usuario", referencedColumnName = "id")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = "Cliente.porNivel",
                    query = "select c FROM Cliente c WHERE c.nivel LIKE ?1"
            )
            ,
            
            @NamedQuery( // é mais interessante colocar essa query em usuário?
                    name = "Cliente.porNome",
                    query = "select c FROM Cliente c WHERE c.nome LIKE ?1 ORDER BY c.nome DESC"
            )
        }
)

@NamedNativeQueries(
        @NamedNativeQuery( // seleciona clientes de acordo com o comparecimento de horario marcado
                name = "Cliente.porComparecimento",
                query = "SELECT DISTINCT "
                + "u.id, u.disc_usuario, u.dt_data_nascimento, u.txt_login, u.txt_nome, u.txt_senha, u.foto "
                + "FROM tb_usuario u "
                + "INNER JOIN tb_horarios_marcados hr on u.id = hr.id_cliente "
                + "INNER JOIN tb_servico s on s.id = hr.id_servico "
                + "WHERE hr.comparecimento = ?1",
                resultClass = Cliente.class
        )
)
public class Cliente extends Usuario implements Serializable {

    @Column(name = "nivel", nullable = true/*, length = 4*/)
    private int nivel;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_horario", referencedColumnName = "ID")
    private List<HorarioMarcado> horarios;

    // getters
    public int getNivel() {
        return nivel;
    }

    public List<HorarioMarcado> getHorarios() {
        return horarios;
    }

    // setters
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void addHorarios(HorarioMarcado horario) {
        if (this.horarios == null) {
            this.horarios = new ArrayList<>();
        }
        this.horarios.add(horario);
    }

    public void setHorarios(List<HorarioMarcado> horarios) {
        this.horarios = horarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }

        Cliente other = (Cliente) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
