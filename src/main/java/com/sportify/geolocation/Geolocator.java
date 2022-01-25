package com.sportify.geolocation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Geolocator {

    private final String apiKey = this.getApiKey();
    private double lat = -1;
    private double lng = -1;
    private String lastAddress = "";
    private static Geolocator geolocatorInstance = null;


    public double getLat(String address){
        if (!address.equals(lastAddress)){ //controlla se la richiesta è gia stata fatta, se è così non ricalcola di nuovo le coordinate
            this.lat = -1;
        }
        if (this.lat == -1)//Se la coordinata richiesta è ancora "-1" allora procede a ricalcolarla. Se dopo il calcolo sarà ancora
            // uguale a -1 c'è stato un errore nel calcolo
           this.setCoordinates(address);
        return this.lat;
    }

    public double getLng(String address){ //controlla se la richiesta è gia stata fatta, se è così non ricalcola di nuovo le coordinate
        if (!address.equals(lastAddress)){
            this.lng = -1;
        }
        if (this.lng == -1)
            this.setCoordinates(address); //Se la coordinata richiesta è ancora "-1" allora procede a ricalcolarla. Se dopo il calcolo sarà ancora
        // uguale a -1 c'è stato un errore nel calcolo
        return this.lng;
    }


    private void setCoordinates(String address) {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String geocodingResource = "https://geocode.search.hereapi.com/v1/geocode";
        String requestUri = geocodingResource + "?apiKey=" + apiKey + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse<String> geocodingResponse;
        try {
            geocodingResponse = httpClient.send(geocodingRequest,
                    HttpResponse.BodyHandlers.ofString());
            double[] response = this.parseResponse(Objects.requireNonNull(geocodingResponse).body());
            if (response.length < 2) { //se le due coordinate non vengono ottenute
                this.lat = -1;
                this.lng = -1;
                return;
            }
            this.lat = response[0];
            this.lng = response[1];
            this.lastAddress = address;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }


    }

    private double[] parseResponse(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        JsonNode item = items.get(0);
        if (item == null)
            return new double[0];
        JsonNode position = item.get("position");
        double latitude = position.get("lat").asDouble();
        double longitude = position.get("lng").asDouble();
        return new double[]{latitude, longitude};

    }


    private String getApiKey(){
        try (InputStream input = new FileInputStream("src/main/resources/com/sportify/geolocation/ApiKey.properties")) {

            Properties prop = new Properties();

            // carica il file properties
            prop.load(input);
            //ritorna la connessione al DB specificato nel file DB.properties
            return prop.getProperty("api.key");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Geolocator(){}

    public static Geolocator getInstance(){
        if (geolocatorInstance == null)
            geolocatorInstance = new Geolocator();
        return geolocatorInstance;
    }

}
