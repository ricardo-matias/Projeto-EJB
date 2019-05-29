package com.FazTudo2.ejb.Entidade;

import com.FazTudo2.ejb.Entidade.HorarioMarcado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-05-29T11:01:30")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, HorarioMarcado> horarios;
    public static volatile SingularAttribute<Cliente, Integer> nivel;

}