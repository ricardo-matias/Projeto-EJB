/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Entidade;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ALUNO
 */
@Embeddable
@Access(AccessType.FIELD)

public class Endereco implements Serializable {

    @Column(name = "end_txt_cep", length = 8, nullable = false)
    private String cep;
    
    @Column(name = "end_txt_rua", length = 50, nullable = false)
    private String rua;

    @Column(name = "end_numero", length = 5, nullable = false)
    private Integer numero;

    @Column(name = "end_txt_complemento", length = 10, nullable = true)
    private String complemento;
    
    @Column(name = "end_txt_bairro", length = 100, nullable = false)
    private String bairro;
    
    @Column(name = "end_txt_cidade", length = 100, nullable = false)
    private String cidade;
    
    @Column(name = "end_txt_estado", length = 50, nullable = false)
    private String estado;
    
    // getters 

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getRua() {
        return rua;
    }
    
    
    
    // setters

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRua(String rua) {
        this.rua = rua;
    } 
    
}
