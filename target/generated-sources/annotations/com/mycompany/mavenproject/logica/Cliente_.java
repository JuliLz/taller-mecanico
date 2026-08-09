package com.mycompany.mavenproject.logica;

import com.mycompany.mavenproject.logica.Auto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-08-09T20:31:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, Long> documento;
    public static volatile ListAttribute<Cliente, Auto> listaAutos;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, Long> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;

}