package edu.escuelaing.arem.ASE.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.escuelaing.arem.ASE.app.annotations.Component;
import edu.escuelaing.arem.ASE.app.annotations.ErrorMapping;
import edu.escuelaing.arem.ASE.app.annotations.RequestMapping;


/**
 * Controlador para manejar solicitudes relacionadas con recursos HTML, CSS y JavaScript.
 */
@Component
public class HtmlController {

    LoadResources l = new LoadResources();

    /**
     * metodo para la obtencion de recursos simple tipo html
     * @return una cadena que representa la respuesta HTTP con el saludo.
     */
    @RequestMapping(value = "/hello")
    public static String hello() {
        return  "HTTP/1.1 200 \r\n" +
                "Content-Type:text/html\r\n" +
                "\r\n"+
                "Greetings from Spring Boot!";
    }

    

    /**
     * metodo para la obtencion de recursos tipo html
     * @return una cadena que representa la respuesta HTTP con el contenido del archivo HTML.
     * @throws IOException exepcion
     */
    @RequestMapping(value = "/index")
    public static String index() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("text/html");
        l.setFile("index.html");
        return l.file();
    }

    /**
     * metodo para el manejo de urls no validas
     * @return una cadena que representa la respuesta HTTP con el contenido del archivo de error 404.
     * @throws IOException exepcion
     */
    @ErrorMapping()
    public static String error() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("text/html");
        l.setFile("404.html");
        return l.file();
    }

    /**
     * metodo para la obtencion de recursos tipo css
     * @return una cadena que representa la respuesta HTTP con el contenido del archivo CSS.
     * @throws IOException exepcion
     */
    @RequestMapping(value = "/styles.css")
    public static String css() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("text/css");
        l.setFile("styles.css");
        return l.file();
    }

    /**
     * metodo para la obtencion de recursos tipo javascript
     * @return una cadena que representa la respuesta HTTP con el contenido del archivo JavaScript.
     * @throws IOException exepcion
     */
    @RequestMapping(value = "/script.js")
    public static String js() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("text/javascript");
        l.setFile("script.js");
        return l.file();
    }
}