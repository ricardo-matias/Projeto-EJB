package com.FazTudo2.ejb.Entidade;

import com.FazTudo2.ejb.Entidade.Cliente;
import com.FazTudo2.ejb.Entidade.Servico;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-05-30T20:50:21")
@StaticMetamodel(HorarioMarcado.class)
public class HorarioMarcado_ extends Entidade_ {

    public static volatile SingularAttribute<HorarioMarcado, Cliente> cliente;
    public static volatile SingularAttribute<HorarioMarcado, Date> data;
    public static volatile SingularAttribute<HorarioMarcado, Boolean> comparecimento;
    public static volatile SingularAttribute<HorarioMarcado, Servico> servico;

}