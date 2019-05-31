/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Servicos;

import com.FazTudo2.ejb.Entidade.Categoria;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author marcos
 */
@Stateless(name = "ejb/CategoriaServico")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class CategoriaServico extends ServicoBean<Categoria> {

    @PostConstruct
    public void init() {
        super.setClasse(Categoria.class);
    }

    @Override
    public Categoria criar() {
        return new Categoria();
    }
    
    public void persistirCategoria(@Valid Categoria categoria) {
        entityManager.persist(categoria);
    }
    
    @TransactionAttribute(SUPPORTS)
    public Categoria consultarCategoriaPorId(@NotNull Long idCategoria) {
        Categoria c = new Categoria();
        c = super.consultarPorId(idCategoria);
        return c;
    }
    
    /*public boolean existe(@NotNull Categoria entidade) {
        TypedQuery<Categoria> query
                = entityManager.createNamedQuery("Categoria.porNome", Categoria.class);
        query.setParameter("nome", entidade.getNome());
        return !query.getResultList().isEmpty();
    }*/

    public Categoria atualizaCategoria(Categoria categoria) {
        categoria = entityManager.merge(categoria);
        entityManager.flush();
        return categoria;
    } 
    
    public List<Categoria> categoriaPorNome(String nome) {
        TypedQuery<Categoria> query = entityManager.createNamedQuery("Categoria.porNome", Categoria.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }
}
