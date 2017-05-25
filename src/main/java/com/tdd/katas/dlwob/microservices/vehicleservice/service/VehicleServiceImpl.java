package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleCustomerDataService vehicleCustomerDataService;

    @Autowired
    private CustomerDataRestServiceProxy customerDataRestServiceProxy;

    @Autowired
    private VehicleDataRestServiceProxy vehicleDataRestServiceProxy;

    @Autowired
    private PartDataRestServiceProxy partDataRestServiceProxy;


    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {


        VehicleCustomerData vehicleCustomerData= vehicleCustomerDataService.getVehicleCustomerData(vinCode);

         if(vehicleCustomerData==null)
             return null;

        CustomerData customerData = customerDataRestServiceProxy.getCustomerData(vehicleCustomerData.getCustomerId());
        VehicleData vehicleData = vehicleDataRestServiceProxy.getVehicleData(vinCode);
        List<PartData> partDataList=partDataRestServiceProxy.getPartDataList(vinCode);

        VehicleInformation dto=new VehicleInformation();

        dto.setCustomerData(customerData);
        dto.setVehicleData(vehicleData);
        dto.setPartsList(partDataList);
        dto.setVin(vinCode);

        return dto;

    }


}
