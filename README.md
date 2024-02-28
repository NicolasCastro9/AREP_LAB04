# Arquitecturas de Servidores de Aplicaciones, Meta protocolos de objetos, Patrón IoC, Reflexión
Este proyecto es un servidor web básico implementado en Java que puede servir archivos estáticos e imágenes PNG. Además, proporciona un marco de Inversión de Control (IoC) para la construcción de aplicaciones web a partir de Plain Old Java Objects (POJOs).

## Clases
HttpServer: Implementa el servidor HTTP que gestiona las solicitudes entrantes y las dirige a los controladores correspondientes.

LoadResources: Se utiliza para cargar recursos del sistema de archivos y servirlos como respuestas HTTP.

ImgController: Controlador para manejar solicitudes relacionadas con recursos de imágenes.

HtmlController: Controlador para manejar solicitudes relacionadas con recursos HTML, CSS y JavaScript.

RequestMapping: Anotación para mapear un método de un controlador a una ruta específica.

ErrorMapping: Anotación para marcar un método como un manejador de errores.

Component:  Anotación para marcar una clase como componente.
## Pre-Requisitos

Java JDK y Maven instalado en el sistema.
Conocimiento básico de Java y programación web.

## Instrucciones de uso

1. Clona el repositorio a tu máquina local:
   ```
   git clone https://github.com/NicolasCastro9/AREP_LAB04.git
   ```
2. En la consola de comandos navegar hasta el directorio donde se encuentra el pom del proyecto
   ```
   cd mitercera-app
   ```
3. Ejecuta el siguiente comando para compilar el proyecto y descargar las dependencias definidas en el archivo
   ```
   mvn clean install
   ```
4. Ejecuta el proyecto con el siguiente comando
  ```
   java -cp target/classes edu.escuelaing.arem.ASE.app.App
   ```
5. En el navegador escribir las siguientes rutas, primero para ver los archivos estaticos
    ```
   http://localhost:35000/index
   ```
    
   ![image](https://github.com/NicolasCastro9/AREP_LAB04/assets/98556822/fc1bec72-6171-4b9d-8067-67083909e95f)

   ```
   http://localhost:35000/styles.css
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB04/assets/98556822/e77bfafa-ac20-4d05-b587-b6656a0b3db9)

   ```
   http://localhost:35000/img
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB04/assets/98556822/4fac4470-db50-45fd-8547-bc47d9d003dc)

   ```
   http://localhost:35000/script.js
   ```
   ![image](https://github.com/NicolasCastro9/AREP_LAB04/assets/98556822/810ef2cb-0c8e-4ede-a5c8-25a84ad7e4b3)

   Metodo de prueba para manejar una soilictud GET
   ```
   http://localhost:35000/hello
   ```   
   ![image](https://github.com/NicolasCastro9/AREP_LAB04/assets/98556822/50481ef9-40aa-4bab-84c8-0294eaea1206)



5. Ver MANEJO DE SOLICITUDES GET Y POST con postman

   ```
   http://localhost:35000/hello
   ```  
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/63ddf133-f95c-4e31-b10a-9a7ec3ac7bb9)
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/80d99865-3a05-4614-b95d-7bbf6e6b75ac)
   ```
   http://localhost:35000/echo
   ```  
   ![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/dcb8ca70-1d3f-4f8e-9df5-e93788621d60)


8. Obtener información de una pelicula como un formato JSON Ejemplo:

   ```
   http://localhost:35000/title?name=TED
   ```  
![image](https://github.com/NicolasCastro9/AREP_LAB03/assets/98556822/4c14fd5b-d65a-4631-882d-cfa05a45a5ab)


9. Ejecutando pruebas
   ```
   mvn test
   ``` 
10. Generar JavaDoc
   ```
   mvn javadoc:javadoc
   ``` 


## CONSTRUIDO CON

MAVEN -  framework de gestión de proyectos de software

## Dependencias
JUnit: Framework de pruebas unitarias para Java.

Gson: Biblioteca de Google para trabajar con JSON en Java.

## Autor
### Nicolás Castro Jaramillo

## Licencia
Este proyecto está bajo la licencia MIT.
