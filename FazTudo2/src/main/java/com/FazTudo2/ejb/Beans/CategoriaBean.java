/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FazTudo2.ejb.Beans;

import com.FazTudo2.ejb.Entidade.Categoria;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author davi
 */
@Stateless(name = "CategoriaBean")
@LocalBean
@ValidateOnExecution(type = ExecutableType.ALL)
public class CategoriaBean {

    @PersistenceContext(name = "exemplo_13_ejb", type = TRANSACTION)
    protected EntityManager em;

    public Categoria consultarCategoriaPorId(@NotNull Long id) {

        return em.find(Categoria.class, id);
    }

    @TransactionAttribute(SUPPORTS)
    public Categoria criarCategoria() {

        return new Categoria();
    }

    public void persistirCategoria(Categoria categoria) {

        em.persist(categoria);
    }

    public Categoria atualizaCategoria(Categoria categoria) {

        categoria = em.merge(categoria);
        em.flush();
        return categoria;
    }

    public boolean existeCategoria(@NotNull Categoria categoria) {
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.porNome", Categoria.class);
        query.setParameter("nome", categoria.getNome());
        return !query.getResultList().isEmpty();
    }

    public List<Categoria> categoriaPorNome() {

        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.porNome", Categoria.class);

        return query.getResultList();

    }

    public List<Categoria> aluguelPorPessoa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
