package edu.escuelaing.arem.ASE.app.controller;
import javax.imageio.ImageIO;

import edu.escuelaing.arem.ASE.app.server.HttpServer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


/**
 * Esta clase se utiliza para cargar recursos del sistema de archivos y servirlos como respuestas HTTP.
 */
public class LoadResources {


    private static LoadResources _instance = new LoadResources();

    /**
     * Devuelve la única instancia de la clase LoadResources.
     * @return instancia única de LoadResources.
     */
    public static LoadResources getInstance(){
        return _instance;
    }

    private String type;
    private String file;


    /**
     * Genera la cabecera HTTP con el tipo de contenido especificado.
     * @return cabecera HTTP.
     */
    public String head() {
        return "HTTP/1.1 200 \r\n" +
                "Content-Type:"+  getType() +"\r\n" +
                "\r\n";
    }


    /**
     * Lee el contenido de un archivo HTML y lo devuelve como una cadena.
     * @return contenido del archivo HTML.
     */
    public String bodyHtml() {
        byte[] file;
        try{
            file = Files.readAllBytes(Paths.get("src/main/resources/public/" + getFile()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return new String(file);
    }

    /**
     * Obtiene el nombre del archivo actual.
     * @return nombre del archivo.
     */
    public String getFile(){
        return file;
    }

    /**
     * Establece el tipo de contenido para la respuesta HTTP.
     * @param t tipo de contenido.
     */
    public void setType(String t){
        this.type = t;
    }

    /**
     * Obtiene el tipo de contenido de la respuesta HTTP.
     * @return tipo de contenido.
     */
    public String getType(){
        return type;
    }

    /**
     * Establece el archivo actual para ser servido.
     * @param f nombre del archivo.
     */
    public void setFile(String f){
        this.file = f;
    }

    /**
     * Lee el contenido de un archivo de imagen (PNG) y lo devuelve como una respuesta HTTP.
     * @return respuesta HTTP con el contenido de la imagen PNG.
     * @throws IOException si hay un error al leer el archivo.
     */
    public String bodyImg() throws IOException {
        String response = "HTTP/1.1 200 \r\n" +
                "Content-Type: image/png \r\n" +
                "\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/public/"+ getFile()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;
    }

     /**
     * Lee el contenido de un archivo de imagen (GIF) y lo devuelve como una respuesta HTTP.
     * @return respuesta HTTP con el contenido de la imagen GIF.
     * @throws IOException si hay un error al leer el archivo.
     */
    public String bodyGif() throws IOException {
        String response = "HTTP/1.1 200 \r\n" +
                "Content-Type: image/gif \r\n" +
                "\r\n";
        try{
            InputStream inputStream = getResourceAsStream(getFile());
            if(inputStream != null){
                BufferedImage bufferedImage = ImageIO.read(new File("src/main/resources/public/"+ getFile()));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "gif", byteArrayOutputStream);
                HttpServer server = HttpServer.getInstance();
                DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
                dataOutputStream.writeBytes(response);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    dataOutputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
            }
            return response;
        }catch (IOException e) {
            // Manejar la excepción adecuadamente
            e.printStackTrace(); // Opcional: imprime la traza de la excepción para depuración
            return "HTTP/1.1 500 Internal Server Error\r\n\r\n"; // Devuelve un código de error HTTP 500 en caso de excepción
        }
        
    }

    /**
     * Sirve el archivo según su tipo de contenido.
     * @return respuesta HTTP con el contenido del archivo.
     * @throws IOException si hay un error al leer el archivo.
     */
    public String file() throws IOException {
        if(type.startsWith("text")){
            return head() + bodyHtml();
        }else if(type.startsWith("gif")) {
            return bodyGif();
        }else{
            return bodyImg();
        }
    }

    /**
     * Obtiene el flujo de entrada del archivo desde el sistema de archivos.
     * @param filename nombre del archivo.
     * @return flujo de entrada del archivo.
     */
    private static InputStream getResourceAsStream(String filename) {
        return HttpServer.class.getResourceAsStream("src/main/resources/public/" + filename);
    }
}
