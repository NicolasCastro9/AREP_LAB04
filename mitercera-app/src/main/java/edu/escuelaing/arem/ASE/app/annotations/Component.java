package edu.escuelaing.arem.ASE.app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Esta anotación se utiliza para marcar una clase como un componente.
 * Los componentes marcados con esta anotación pueden ser detectados automáticamente
 * y gestionados de alguna manera especial por el contenedor de la aplicación.
 * 
 * Esta anotación debe ser aplicada a la declaración de una clase.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}