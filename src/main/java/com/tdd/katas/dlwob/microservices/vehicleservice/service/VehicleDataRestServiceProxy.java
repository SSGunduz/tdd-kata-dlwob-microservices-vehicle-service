package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
class VehicleDataRestServiceProxy {

    public VehicleDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder){
        restTemplateBuilder.build();
    }

    public VehicleData getVehicleData(String vinCode) {
        return null;
    }

}
