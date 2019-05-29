/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ricardo
 */
@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "disc_usuario",
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)

public abstract class Usuario extends Entidade implements Serializable {

    //@Pattern(regexp = "\p{Upper}{1}\p{Lower}+\p{Space}*?")
    //@Pattern(regexp = "(?=^.{0,40}$)^[a-zA-Z-]+\\s[a-zA-Z-]+$")
    @Column(name = "txt_nome", length = 100, nullable = false)
    protected String nome;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_data_nascimento", nullable = true /* nullable true para testes*/)
    protected Date dataNascimento;

    @NotBlank
    @Column(name = "txt_login", length = 30, nullable = false)
    protected String login;

    @Pattern(regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})", 
            message = "{exemplo.jpa.Usuario.senha}")
    @Column(name = "txt_senha", length = 20, nullable = false)
    protected String senha;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="tb_telefone_usuario",joinColumns = @JoinColumn(name="id_usuario"))
    @Column(name = "txt_num_telefone", length = 20)
    protected Collection<String> telefones;
    
    @Embedded
    protected Foto foto;
    
    public Usuario(){}

    // getters

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Foto getFoto() {
        return foto;
    }
    
    public Collection<String> getTelefones() {
        return telefones;
    }
    
    // setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
    public void addTelefone(String telefone) {
        if(this.telefones == null){
            this.telefones = new ArrayList<>();
        }
        this.telefones.add(telefone);
    }
    
}
