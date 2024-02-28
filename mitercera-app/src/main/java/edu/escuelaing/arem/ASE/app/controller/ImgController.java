package edu.escuelaing.arem.ASE.app.controller;

import java.io.IOException;

import edu.escuelaing.arem.ASE.app.annotations.Component;
import edu.escuelaing.arem.ASE.app.annotations.RequestMapping;

/**
 * Controlador para manejar solicitudes relacionadas con recursos de imágenes.
 */
@Component
public class ImgController {

    LoadResources l = new LoadResources();

    /**
     * metodo para la obtencion de recursos tipo imagen
     * @return contenido de "image.jpg".
     * @throws IOException si hay errpr al leer el archivp
     */
    @RequestMapping(value = "/img")
    public static String img() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("image/jpg");
        l.setFile("image.jpg");
        return l.file();
    }

    /**
     * metodo para la obtencion de recursos tipo gif (No funciona)
     * @return Contenido de "tiburon.gif".
     * @throws IOException si hay un error al leer el archivo.
     */
    @RequestMapping(value = "/gif")
    public static String gif() throws IOException {
        LoadResources l = LoadResources.getInstance();
        l.setType("image/gif");
        l.setFile("tiburon.gif");
        return l.file();
    }


}
