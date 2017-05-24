package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
@RestController
@RequestMapping(VehicleDataController.URL_MAPPING)
public class VehicleDataController {

    public static final String URL_MAPPING = "/vehicle-data";

    @RequestMapping("/{vinCode}")
    public void getVehicleData(String vinCode){
        return ;
    }
}
