package com.eventusapp.eventus.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
@Service
public class GeolocationService {

    @Autowired
    EvironmentVariablesService evironmentVariablesService;

    public String getCoordinates(String query) {
        String API_KEY = evironmentVariablesService.getGeolocationAPIKey();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, query).await();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        context.shutdown();
        return gson.toJson(results[0].geometry.location);
    }
}
