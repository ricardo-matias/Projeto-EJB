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
import org.hibernate.validator.constraints.NotBlank;
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
                    query = "select d FROM DonoEstabelecimento d WHERE d.nome LIKE ?1 ORDER BY d.nome DESC"
            ),
            @NamedQuery(
                    name = "DonoEstabelecimento.porId",
                    query = "select d FROM DonoEstabelecimento d WHERE d.id LIKE ?1 ORDER BY d.id DESC"
            ),
            
            @NamedQuery(
                    name = "DonoEstabelecimento.porCpf",
                    query = "select d FROM DonoEstabelecimento d WHERE d.cpf LIKE :cpf"
            )
        }
)
public class DonoEstabelecimento extends Usuario implements Serializable {
    @CPF
    @NotBlank
    @Column(name = "txt_cpf", nullable = false, length = 15, unique = true)
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
}
