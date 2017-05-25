package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
public class CustomerDataRestServiceProxy {

    private RestTemplate restTemplate;

    private String customerDataServiceUrl;

    public CustomerDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder,
                                        @Value("${services.customer-data-service.url}") String customerDataServiceUrl){
        this.customerDataServiceUrl=customerDataServiceUrl;
        restTemplate=restTemplateBuilder.build();
    }

    public CustomerData getCustomerData(String customerId) throws HttpServerErrorException, HttpClientErrorException {

       ResponseEntity<CustomerData> response;

        try{

            response=restTemplate.getForEntity(customerDataServiceUrl+"/"+customerId,CustomerData.class);

        }catch(HttpClientErrorException e){

            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
            else
                throw e;
        }

        return response.getBody();
    }

}
