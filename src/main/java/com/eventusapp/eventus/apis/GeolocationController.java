package com.eventusapp.eventus.apis;
import com.eventusapp.eventus.annotations.AuthorizationRequired;
import com.eventusapp.eventus.apis.responses.LoginResponse;
import com.eventusapp.eventus.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1")
@RestController
public class GeolocationController {

    @Autowired
    GeolocationService geolocationService;

    @AuthorizationRequired
    @RequestMapping(value = "/get-coordinates", method = {RequestMethod.GET})
    public ResponseEntity<Object> getLocationCoordinates(@RequestParam String location) {
        String coordinates= geolocationService.getCoordinates(location);
        return new ResponseEntity<>(coordinates, HttpStatus.OK);
    }

}
