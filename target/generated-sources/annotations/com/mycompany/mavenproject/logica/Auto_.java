package com.mycompany.mavenproject.logica;

import com.mycompany.mavenproject.logica.Cliente;
import com.mycompany.mavenproject.logica.ServicioMecanico;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-09T20:31:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Auto.class)
public class Auto_ { 

    public static volatile SingularAttribute<Auto, String> marca;
    public static volatile SingularAttribute<Auto, Cliente> cliente;
    public static volatile SingularAttribute<Auto, String> dominio;
    public static volatile SingularAttribute<Auto, Integer> id;
    public static volatile ListAttribute<Auto, ServicioMecanico> serviciosRealizados;
    public static volatile SingularAttribute<Auto, String> modelo;
    public static volatile SingularAttribute<Auto, Integer> capacidad;

}