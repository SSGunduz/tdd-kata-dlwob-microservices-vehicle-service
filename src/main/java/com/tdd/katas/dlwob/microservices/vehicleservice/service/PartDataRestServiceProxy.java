package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
public class PartDataRestServiceProxy {

    private final String partDataServiceUrl;
    private RestTemplate restTemplate;


    public PartDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder,
                                    @Value("${services.part-data-service.url}") String partDataServiceUrl){
        this.partDataServiceUrl=partDataServiceUrl;
        restTemplate = restTemplateBuilder.build();
    }


    public List<PartData> getPartDataList(String vinCode) throws HttpServerErrorException, HttpClientErrorException{

        ResponseEntity<PartData[]> response;
         try{

             response = restTemplate.getForEntity(partDataServiceUrl + "/" + vinCode, PartData[].class);
         }catch(HttpClientErrorException e)
         {
             if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                 return null;
             else
                 throw e;
         }

         return Arrays.asList(response.getBody());

    }


}
