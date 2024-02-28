package edu.escuelaing.arem.ASE.app.server;

import edu.escuelaing.arem.ASE.app.annotations.Component;
import edu.escuelaing.arem.ASE.app.annotations.ErrorMapping;
import edu.escuelaing.arem.ASE.app.annotations.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Servidor HTTP que gestiona solicitudes entrantes y las dirige a los controladores correspondientes.
 */
public class HttpServer {

    private static HttpServer _instance = new HttpServer();

    private static OutputStream outputStream = null;

    // Mapa que almacena los servicios mapeados a las rutas solicitadas
    public static Map<String, Method> services = new HashMap<>();
    //error
    public static Method error = null;


    /**
     * Obtiene la instancia única del servidor HTTP.
     * 
     * @return la instancia única del servidor HTTP.
     */
    public static HttpServer getInstance(){
        return _instance;
    }



    /**
     * Inicia el servidor HTTP.
     * 
     * @param args los argumentos de la línea de comandos (no se utilizan en este caso).
     * @throws IOException si ocurre un error de entrada/salida durante la ejecución del servidor.
     * @throws ClassNotFoundException si no se puede encontrar la clase especificada durante la ejecución del servidor.
     * @throws InvocationTargetException si se produce un error durante la invocación de un método durante la ejecución del servidor.
     * @throws IllegalAccessException si se produce un acceso ilegal a un método durante la ejecución del servidor.
     */
    public void run(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        String raiz = "edu.escuelaing.arem.ASE.app.controller";
        Set<Class<?>> classSet = getClasses(raiz);
        for(Class<?> clazz : classSet){
            boolean hasMyAnnotation = clazz.isAnnotationPresent(Component.class);
            if(hasMyAnnotation){
                String className = clazz.getName();
                Class<?> c = Class.forName(className);
                Method[] m = c.getMethods();
                for(Method ms : m){
                    if(ms.isAnnotationPresent(RequestMapping.class)){
                        String key = ms.getAnnotation(RequestMapping.class).value();
                        services.put(key, ms);
                        System.out.println("key: " + key + " value: " + ms);
                    }else if(ms.isAnnotationPresent(ErrorMapping.class)){
                        System.out.println(ms);
                        error = ms;
                    }
                }
            }
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine = null;
            String path;
            outputStream = clientSocket.getOutputStream();
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("GET")) {
                    path = inputLine.split(" ")[1];
                    if(!Objects.equals(path, "/favicon.ico")){
                        if(services.containsKey(path)){
                            outputLine = (String) services.get(path).invoke(null);
                        }else{
                            outputLine = (String) error.invoke(null);
                        }
                    }
                }
                if (!in.ready()) {
                    break;
                }
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Obtiene la secuencia de salida del servidor.
     * 
     * @return la secuencia de salida del servidor.
     */
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Busca las clases en un paquete especificado y agrega los métodos anotados a la tabla de servicios.
     * 
     * @param raiz el nombre del paquete que se va a analizar.
     * @return un conjunto de todas las clases en el directorio especificado.
     */
    private static Set<Class<?>> getClasses(String raiz){
        Set<Class<?>> classes = new HashSet<>();
        String path = raiz.replace(".", "/");
        try{
            File file = new File(getClassPaths() + "/" + path);
            if(file.exists() && file.isDirectory()){
                for(File classFile : file.listFiles()){
                    if(classFile.isFile() && classFile.getName().endsWith(".class")){
                        String className = raiz + "." + classFile.getName().replace(".class", "");
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return classes;
    }


    /**
     * metodo que busca la ubicacion de la carpeta target
     * @return path de la carpeta target
     */
    private static String getClassPaths() {
        String classPath = System.getProperty("java.class.path");
        String[] classPaths = classPath.split(System.getProperty("path.separator"));
        return classPaths[0];
    }

}

