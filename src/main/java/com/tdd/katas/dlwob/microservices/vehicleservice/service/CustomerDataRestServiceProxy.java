package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.CustomerDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
public class CustomerDataRestServiceProxy {

    private RestTemplate restTemplate;

    public CustomerDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder){
        restTemplate=restTemplateBuilder.build();
    }

    public CustomerData getCustomerData(String customerId) throws HttpServerErrorException, HttpClientErrorException {
        try{
            restTemplate.getForEntity(CustomerDataController.URL_MAPPING+"/"+customerId,CustomerData.class);
        }catch(HttpClientErrorException e){

            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
            else
                throw e;
        }

        throw new UnsupportedOperationException("Not implemented");
    }

}
