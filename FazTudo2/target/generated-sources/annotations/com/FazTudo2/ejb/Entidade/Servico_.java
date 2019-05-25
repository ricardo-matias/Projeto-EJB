package com.FazTudo2.ejb.Entidade;

import com.FazTudo2.ejb.Entidade.Categoria;
import com.FazTudo2.ejb.Entidade.Estabelecimento;
import com.FazTudo2.ejb.Entidade.Foto;
import com.FazTudo2.ejb.Entidade.HorarioMarcado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-05-25T08:44:15")
@StaticMetamodel(Servico.class)
public class Servico_ { 

    public static volatile ListAttribute<Servico, HorarioMarcado> horarios;
    public static volatile SingularAttribute<Servico, Foto> foto;
    public static volatile SingularAttribute<Servico, Estabelecimento> estabelecimento;
    public static volatile ListAttribute<Servico, Categoria> categorias;
    public static volatile SingularAttribute<Servico, Double> valor;
    public static volatile SingularAttribute<Servico, String> nome;
    public static volatile SingularAttribute<Servico, Long> id;
    public static volatile SingularAttribute<Servico, String> descricao;

}