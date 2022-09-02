package com.eventusapp.eventus.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

@Service
public class EvironmentVariablesService {

    private Dotenv dotenv =  Dotenv.load();


    public String getDotEnvSecretKey(){
        return dotenv.get("SECRET_KEY");
    }

    public String getGeolocationAPIKey(){
        return dotenv.get("GOOGLE_GEOLOCATION_API_KEY");
    }



}
