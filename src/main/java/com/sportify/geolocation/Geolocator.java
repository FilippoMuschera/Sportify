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
    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = Geolocator.getApiKey();

    public static double[] getCoordinates(String address) {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse<String> geocodingResponse = null;
        try {
            geocodingResponse = httpClient.send(geocodingRequest,
                    HttpResponse.BodyHandlers.ofString());
            return parseResponse(Objects.requireNonNull(geocodingResponse).body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }


        return new double[0]; //array vuoto
    }

    private static double[] parseResponse(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");



        JsonNode item = items.get(0);
        if (item == null)
            return new double[0];
        JsonNode position = item.get("position");
        double lat = position.get("lat").asDouble();
        double lng = position.get("lng").asDouble();
        return new double[]{lat, lng};

    }


    private static String getApiKey(){
        try (InputStream input = new FileInputStream("src/main/resources/com.sportify.geolocation/ApiKey.properties")) {

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
}
