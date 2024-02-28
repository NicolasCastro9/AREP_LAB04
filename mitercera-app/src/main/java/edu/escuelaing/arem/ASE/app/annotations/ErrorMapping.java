package edu.escuelaing.arem.ASE.app.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Esta anotación se utiliza para marcar un método como un manejador de errores.
 * Los métodos marcados con esta anotación pueden ser identificados automáticamente
 * y utilizados para manejar errores o solicitudes no válidas en la aplicación.
 * Esta anotación debe ser aplicada a la declaración de un método.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorMapping {

}
