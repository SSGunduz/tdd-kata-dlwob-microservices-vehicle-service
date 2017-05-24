package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public ResponseEntity<List<PartData>> getPartData(@PathVariable String vinCode){

        List<PartData> actualPartDataList;

        try{
         actualPartDataList=  partDataService.getPartData(vinCode);

        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(actualPartDataList==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(actualPartDataList,HttpStatus.OK);


    }




}
