package com.conversor.conexiones;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionHTTP {
    public String conexion(String con)
    {
        String s = "";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(con))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            s = response.body();
            return s;
            //System.out.println(json);

        } catch (NumberFormatException e) {
            System.out.println("Ocurrió un error (http): ");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
