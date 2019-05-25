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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "tb_dono_estabelecimento")
@DiscriminatorValue(value = "D")
@PrimaryKeyJoinColumn(name = "id_usuario", referencedColumnName = "id")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = "DonoEstabelecimento.porNome",
                    query = "select d FROM DonoEstabelecimento d WHERE d.nome LIKE :nome ORDER BY d.nome DESC"
            )
        }
)
public class DonoEstabelecimento extends Usuario implements Serializable {
    @CPF
    @Column(name = "txt_cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Valid
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_estabelecimento_dono_estabelecimento", joinColumns = {
        @JoinColumn(name = "id_usuario")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_estabelecimento")})
    private List<Estabelecimento> estabelecimentos;

    public DonoEstabelecimento() {
    }

    // getters
    public String getCpf() {
        return cpf;
    }

    public List<Estabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    // setters 
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void addEstabelecimento(Estabelecimento estabelecimento) {
        if (estabelecimentos == null) {
            estabelecimentos = new ArrayList<>();
        }

        estabelecimentos.add(estabelecimento);
    }

    public void setEstabelecimentos(List<Estabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DonoEstabelecimento)) {
            return false;
        }

        DonoEstabelecimento other = (DonoEstabelecimento) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
