package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleCustomerDataService vehicleCustomerDataService;

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {

         VehicleCustomerData vehicleCustomerData= vehicleCustomerDataService.getVehicleCustomerData(vinCode);

         if(vehicleCustomerData==null)
             return null;


         throw new UnsupportedOperationException("Not implemented yet");
    }


}
