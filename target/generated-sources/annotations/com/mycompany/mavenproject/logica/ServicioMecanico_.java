package com.mycompany.mavenproject.logica;

import com.mycompany.mavenproject.logica.Auto;
import com.mycompany.mavenproject.logica.Mecanico;
import com.mycompany.mavenproject.logica.TipoServicio;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-10-15T16:45:45", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ServicioMecanico.class)
public class ServicioMecanico_ { 

    public static volatile SingularAttribute<ServicioMecanico, String> descripcion;
    public static volatile SingularAttribute<ServicioMecanico, Mecanico> mecanico;
    public static volatile SingularAttribute<ServicioMecanico, Auto> auto;
    public static volatile SingularAttribute<ServicioMecanico, TipoServicio> tipoServicio;
    public static volatile SingularAttribute<ServicioMecanico, Date> fechaInicio;
    public static volatile SingularAttribute<ServicioMecanico, Integer> id;
    public static volatile SingularAttribute<ServicioMecanico, Boolean> finalizado;
    public static volatile SingularAttribute<ServicioMecanico, Date> fechaFin;
    public static volatile SingularAttribute<ServicioMecanico, Double> importe;

}