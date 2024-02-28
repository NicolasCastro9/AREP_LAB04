package edu.escuelaing.arem.ASE.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import edu.escuelaing.arem.ASE.app.controller.HtmlController;
import edu.escuelaing.arem.ASE.app.controller.LoadResources;
import edu.escuelaing.arem.ASE.app.server.HttpServer;



/**
 * Unit test for simple App.
 */
public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final OutputStream originalOut = System.out;
    private final OutputStream originalErr = System.err;

    @Test
    public void testIndex() {
        // Arrange
        String expectedResponse = "HTTP/1.1 200 \r\nContent-Type:text/html\r\n\r\nGreetings from Spring Boot!";
        
        // Act
        String response = HtmlController.hello();
        
        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testScriptContent() throws IOException {
        // Arrange
        String expectedResponse = "HTTP/1.1 200 \r\nContent-Type:text/javascript\r\n\r\nvar app = (function (){    var click = function (){        alert(\"Diste un click\");    }    return {        click: click    }})();";
        
        // Act
        String response = HtmlController.js();
        
        // Assert
        assertNotEquals(expectedResponse.trim(), response.trim());
    }
    
    @Test
    public void testGetOutputStream() {
        // Arrange
        HttpServer server = new HttpServer();

        // Act
        OutputStream outputStream = server.getOutputStream();

        // Assert
        assertEquals(null, outputStream);
    }

    @Test
    public void testGetClasses() {
        // Arrange
        HttpServer server = new HttpServer();

        // Act
        String classPaths = server.getClassPaths();

        // Assert
        assertNotEquals(null, classPaths);
    }

    
}