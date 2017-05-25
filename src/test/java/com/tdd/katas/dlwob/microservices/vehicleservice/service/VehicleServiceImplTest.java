package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = VehicleServiceImpl.class)
public class VehicleServiceImplTest {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @MockBean
    private VehicleCustomerDataService vehicleCustomerDataService;

    @MockBean
    private CustomerDataRestServiceProxy customerDataRestServiceProxy;

    @MockBean
    private VehicleDataRestServiceProxy vehicleDataRestServiceProxy;

    @MockBean
    private PartDataRestServiceProxy partDataRestServiceProxy;

    /*
        1- Returns null when vin code does not exist
        2- Throws exception when any sub service returns an error
        3- Returns valid data for an existent vin code
     */

    @Test
    public void Returns_null_when_bean_code_does_not_exist(){

       final String NON_EXISTENT_VIN="NON_EXISTENT_VIN";

       given(vehicleCustomerDataService.getVehicleCustomerData(NON_EXISTENT_VIN)).willReturn(null);


       VehicleInformation actualVehicleInformation= vehicleService.getVehicleInformation(NON_EXISTENT_VIN);

       assertNull(actualVehicleInformation);

       verify(vehicleCustomerDataService).getVehicleCustomerData(NON_EXISTENT_VIN);


    }

}
