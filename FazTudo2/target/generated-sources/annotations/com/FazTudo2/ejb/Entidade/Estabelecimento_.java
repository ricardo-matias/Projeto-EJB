package com.FazTudo2.ejb.Entidade;

import com.FazTudo2.ejb.Entidade.DonoEstabelecimento;
import com.FazTudo2.ejb.Entidade.Endereco;
import com.FazTudo2.ejb.Entidade.Foto;
import com.FazTudo2.ejb.Entidade.Servico;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-05-30T20:50:21")
@StaticMetamodel(Estabelecimento.class)
public class Estabelecimento_ extends Entidade_ {

    public static volatile ListAttribute<Estabelecimento, Servico> servicos;
    public static volatile ListAttribute<Estabelecimento, DonoEstabelecimento> donos;
    public static volatile SingularAttribute<Estabelecimento, Endereco> endereco;
    public static volatile SingularAttribute<Estabelecimento, String> nome;
    public static volatile SingularAttribute<Estabelecimento, String> cnpj;
    public static volatile CollectionAttribute<Estabelecimento, String> telefones;
    public static volatile ListAttribute<Estabelecimento, Foto> fotos;
    public static volatile SingularAttribute<Estabelecimento, String> status;

}