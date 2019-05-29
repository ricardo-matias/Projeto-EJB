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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import validacoes.ValidaPalavrao;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "tb_servico")
@Access(AccessType.FIELD)
@NamedQueries(
        {
            @NamedQuery(
                    name = "Servico.porNome",
                    query = "select s FROM Servico s WHERE s.nome LIKE :nome"
            )
        }
)
public class Servico extends Entidade implements Serializable {

    @Column(name = "txt_nome", length = 100, nullable = false)
    private String nome;

    @ValidaPalavrao(message = "{exemplo.jpa.Servico.descricao}")
    @Column(name = "txt_descricao", length = 255, nullable = true)
    private String descricao;

    @Column(name = "valor", nullable = true)
    private Double valor;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "tb_servicos_categorias", joinColumns = {
        @JoinColumn(name = "id_servico")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_categoria")})
    private List<Categoria> categorias;

    @Embedded
    protected Foto foto;

    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioMarcado> horarios;

    // relacionamento estabelecimentos/serviços bidirecional
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estabelecimento", referencedColumnName = "id")
    private Estabelecimento estabelecimento;

    public Servico() {
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<HorarioMarcado> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioMarcado> horarios) {
        this.horarios = horarios;
    }

    public void addHorario(HorarioMarcado horario) {
        if (this.horarios == null) {
            this.horarios = new ArrayList<>();
        }
        this.horarios.add(horario);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Estabelecimento getEstabelecimento() {
        return this.estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public void addCategoria(Categoria categoria) {
        if (this.categorias == null) {
            this.categorias = new ArrayList<>();
        }
        this.categorias.add(categoria);
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
