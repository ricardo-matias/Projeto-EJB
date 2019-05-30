/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "tb_estabelecimento")
@Access(AccessType.FIELD)

@NamedQueries(
        {
            @NamedQuery(
                    name = "Estabelecimento.porStatus",
                    query = "SELECT e FROM Estabelecimento e WHERE e.status LIKE ?1 ORDER BY e.status DESC"
            ),
 
            @NamedQuery(
                    name = "Estabelecimento.porCNPJ",
                    query = "SELECT e FROM Estabelecimento e WHERE e.cnpj LIKE ?1"
            )
        }
)

@NamedNativeQueries(
        {
            @NamedNativeQuery( // seleciona estabelecimentos pelas categorias dos serviços ofertados
                    name = "Estabelecimento.porCategoriasNative",
                    query = "SELECT DISTINCT e.id, e.txt_cnpj, e.txt_nome, e.txt_status, e.end_txt_bairro, e.end_txt_cidade, e.end_txt_complemento, e.end_txt_estado, e.end_numero, e.end_txt_rua from tb_estabelecimento e "
                            + "INNER JOIN tb_servico s on s.id_estabelecimento = e.id "
                            + "INNER JOIN tb_servicos_categorias sc on s.id = sc.id_servico "
                            + "INNER JOIN tb_categoria c on sc.id_categoria = c.id "
                            + "WHERE c.txt_nome = ?1 "
                            + "ORDER BY e.id;",
                    resultClass = Estabelecimento.class
            )
        }
)

public class Estabelecimento extends Entidade implements Serializable {

    @Column(name = "txt_nome", length = 100, nullable = false)
    private String nome;

    //@CNPJ
    @Column(name = "txt_cnpj", length = 25, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "txt_status", length = 45, nullable = false)
    private String status;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tb_foto_estabelecimento", joinColumns = @JoinColumn(name = "id_estabelecimento", nullable = false))
    @Column(name = "foto")
    private List<Foto> fotos;

    @Embedded
    private Endereco endereco;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_telefone_estabelecimento", joinColumns = @JoinColumn(name = "id_estabelecimento", nullable = false))
    @Column(name = "txt_num_telefone")
    protected Collection<String> telefones;

    @Valid
    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Servico> servicos;

    @Valid
    @ManyToMany(mappedBy = "estabelecimentos", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    List<DonoEstabelecimento> donos;

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getStatus() {
        return status;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public List<DonoEstabelecimento> getDonos() {
        return donos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setDonos(List<DonoEstabelecimento> donos) {
        this.donos = donos;
    }

    public void addFoto(Foto foto) {
        if (this.fotos == null) {
            this.fotos = new ArrayList<>();
        }
        this.fotos.add(foto);
    }

    public void addTelefone(String telefone) {
        if (this.telefones == null) {
            this.telefones = new ArrayList<>();
        }
        this.telefones.add(telefone);
    }

    public void addServico(Servico servico) {
        if (this.servicos == null) {
            this.servicos = new ArrayList<>();
        }
        this.servicos.add(servico);
    }

    public void addDono(DonoEstabelecimento dono) {
        if (this.donos == null) {
            this.donos = new ArrayList<>();
        }
        this.donos.add(dono);
    }

}
