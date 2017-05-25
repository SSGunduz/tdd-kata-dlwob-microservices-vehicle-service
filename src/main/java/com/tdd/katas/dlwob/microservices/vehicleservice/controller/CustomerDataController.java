package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
@RestController
@RequestMapping(CustomerDataController.URL_MAPPING)
public class CustomerDataController {

    public final static String URL_MAPPING="/customer-data";

    @Autowired
    private CustomerDataService service;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerData> getCustomerData(@PathVariable String customerId){

        try{
            service.getCustomerData(customerId);
        }catch (Exception e){
            return new ResponseEntity<CustomerData>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        throw new UnsupportedOperationException("Not Implemented");

    }

}
