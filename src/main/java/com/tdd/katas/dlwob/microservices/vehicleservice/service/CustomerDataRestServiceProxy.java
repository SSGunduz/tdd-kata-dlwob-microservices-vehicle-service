package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
public class CustomerDataRestServiceProxy {


    public CustomerDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder){
        restTemplateBuilder.build();
    }

    public CustomerData getCustomerData(String non_existent_vin_code) {
        throw new UnsupportedOperationException("Not implemented");

    }

}
