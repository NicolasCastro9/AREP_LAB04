package edu.escuelaing.arem.ASE.app.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Esta anotación se utiliza para mapear un método de un controlador a una ruta específica.
 * Los métodos marcados con esta anotación pueden ser identificados automáticamente
 * y asociados con rutas de solicitud específicas en la aplicación.
 * Esta anotación debe ser aplicada a la declaración de un método en un controlador.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * Define la ruta de solicitud asociada al método anotado.
     * 
     * @return la ruta de solicitud asociada al método.
     */
    String value();
}
