package com.math.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultorApi {

    //PASO 1: CONSULTA A LA API PARA OBETNER LOS VALORES PRINCIPALES
    public String obtenerDatosGenerales(String url){
        //Conector a la "base de datos"
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        var nuevoJson = "";

        try{
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //Bloque de código que establece la conección con la API y devuelve los valores de RESULTS
            ObjectMapper mapper = new ObjectMapper();
            //--Convierte la cadena JSON a un JsonNode
            JsonNode rootNode = mapper.readTree(json);
            //Se centra en el nodo de Results y agarra el primer valor, pues es una lista
            JsonNode conversionRatesNode = rootNode.path("results").get(0);
            //--Se almacena el valor del conversionRatesNode en el "NUEVO JSON", que utilizaremos para trabajar
            nuevoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(conversionRatesNode);
            //Permite convertir este valor a un "Json", también "embellece" el string

        } catch(IOException | InterruptedException e){
            System.out.println("Algo ocurrió al establecer conección con la API" + e.getMessage());
        }
        return nuevoJson;
    }

    //SE CONSULTA A LA API PARA ENFOCAR EL JSON DE AUTOR
    public String obtenerDatosEspecificos(String url){
        //Conector a la "base de datos"
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        var nuevoJson = "";

        try{
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //Bloque de código que establece la conección con la API y devuelve los valores de RESULTS
            ObjectMapper mapper = new ObjectMapper();
            //--Convierte la cadena JSON a un JsonNode
            JsonNode rootNode = mapper.readTree(json);
            //Se centra en el nodo de Results y agarra el primer valor, pues es una lista
            JsonNode conversionRatesNode = rootNode.path("results").get(0);
            //Se centra en el nodo de Authors
            JsonNode conversionRatesNodeDoble = conversionRatesNode.path("authors").get(0);
            //--Se almacena el valor del conversionRatesNode en el "NUEVO JSON", que utilizaremos para trabajar
            nuevoJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(conversionRatesNodeDoble);
            //Permite convertir este valor a un "Json", también "embellece" el string

        } catch(IOException | InterruptedException e){
            System.out.println("Algo ocurrió al establecer conección con la APIEspecífica" + e.getMessage());
        }
        return nuevoJson;
    }

}
