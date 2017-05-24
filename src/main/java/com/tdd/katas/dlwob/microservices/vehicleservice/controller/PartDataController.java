package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Hexad GmbH on 24/05/2017.
 */

@RestController
@RequestMapping(PartDataController.URL_MAPPING)
public class PartDataController {

    public static final String URL_MAPPING="/part-data";

    @Autowired
    private PartDataService partDataService;

    @RequestMapping("/{vinCode}")
    public ResponseEntity<PartData> getPartData(@PathVariable String vinCode){

        try{
            partDataService.getPartData(vinCode);

        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
    }




}
