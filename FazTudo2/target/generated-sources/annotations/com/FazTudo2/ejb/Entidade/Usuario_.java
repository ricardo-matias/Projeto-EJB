package com.FazTudo2.ejb.Entidade;

import com.FazTudo2.ejb.Entidade.Foto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-05-29T11:01:30")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ extends Entidade_ {

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Foto> foto;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, Date> dataNascimento;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile CollectionAttribute<Usuario, String> telefones;

}