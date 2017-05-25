package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.VehicleDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
class VehicleDataRestServiceProxy {

    private RestTemplate restTemplate;

    public VehicleDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder){
        restTemplate = restTemplateBuilder.build();
    }

    public VehicleData getVehicleData(String vinCode) throws HttpClientErrorException {
        ResponseEntity<VehicleData> response;

        try{
            response =restTemplate.getForEntity(VehicleDataController.URL_MAPPING+"/"+vinCode, VehicleData.class);


        }catch (HttpClientErrorException e) {
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;

            else
                throw e;
        }

        throw new UnsupportedOperationException("Not implemented");

    }

}
