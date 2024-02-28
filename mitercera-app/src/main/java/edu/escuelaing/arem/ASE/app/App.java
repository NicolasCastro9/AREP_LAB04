package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import edu.escuelaing.arem.ASE.app.server.HttpServer;

/**
 * Clase principal de la aplicación.
 * Esta clase contiene el método main que inicia el servidor HTTP.
 */
public class App {

    /**
     * Método principal de la aplicación.
     * Inicia el servidor HTTP y maneja posibles excepciones.
     * 
     * @param args los argumentos de la línea de comandos (no se utilizan en este caso).
     * @throws IOException si ocurre un error de entrada/salida durante la ejecución del servidor.
     * @throws ClassNotFoundException si no se puede encontrar la clase especificada durante la ejecución del servidor.
     * @throws InvocationTargetException si se produce un error durante la invocación de un método durante la ejecución del servidor.
     * @throws IllegalAccessException si se produce un acceso ilegal a un método durante la ejecución del servidor.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        HttpServer server = HttpServer.getInstance();
        server.run(args);

    }
}
