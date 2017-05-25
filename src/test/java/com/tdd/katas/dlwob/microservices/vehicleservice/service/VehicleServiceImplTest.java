package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    @Test
    public void Returns_valid_data_for_an_existent_vin_code(){
        final String AN_EXISTENT_VIN="AN_EXISTENT_VIN";

        final String CUSTOMER_ID="An existent customer id";

        VehicleCustomerData expectedVehicleCustomerData=new VehicleCustomerData();

        CustomerData expectedCustomerData=new CustomerData();

        VehicleData expectedVehicleData=new VehicleData();

        List<PartData> expectedPartDataList=new ArrayList<PartData>();

        expectedPartDataList.add(new PartData());
        expectedPartDataList.add(new PartData());
        expectedPartDataList.add(new PartData());

        expectedPartDataList.get(0).setId("Id 1");
        expectedPartDataList.get(0).setDescription("Description 1");
        expectedPartDataList.get(1).setId("Id 2");
        expectedPartDataList.get(1).setDescription("Description 2");
        expectedPartDataList.get(2).setId("Id 3");
        expectedPartDataList.get(2).setDescription("Description 3");

        expectedVehicleData.setModelId("Lamborghini");
        expectedVehicleData.setPlateNumber("23 bnf 23");

        expectedCustomerData.setId(CUSTOMER_ID);
        expectedCustomerData.setName("Expected Customer name");
        expectedCustomerData.setSurnames("Expected Customer surname");


        expectedVehicleCustomerData.setCustomerId(CUSTOMER_ID);

        given(vehicleCustomerDataService.getVehicleCustomerData(AN_EXISTENT_VIN)).willReturn(expectedVehicleCustomerData);

        given(customerDataRestServiceProxy.getCustomerData(CUSTOMER_ID)).willReturn(expectedCustomerData);

        given(vehicleDataRestServiceProxy.getVehicleData(AN_EXISTENT_VIN)).willReturn(expectedVehicleData);

        given(partDataRestServiceProxy.getPartDataList(AN_EXISTENT_VIN)).willReturn(expectedPartDataList);

        VehicleInformation actualVehicleInformation= vehicleService.getVehicleInformation(AN_EXISTENT_VIN);

        assertNotNull(actualVehicleInformation);
        assertEquals(AN_EXISTENT_VIN,actualVehicleInformation.getVin());
        assertEquals(expectedVehicleData.getModelId(),actualVehicleInformation.getVehicleData().getModelId());
        assertEquals(expectedVehicleData.getPlateNumber(),actualVehicleInformation.getVehicleData().getPlateNumber());
        assertEquals(expectedCustomerData.getId(),actualVehicleInformation.getCustomerData().getId());
        assertEquals(expectedCustomerData.getName(),actualVehicleInformation.getCustomerData().getName());
        assertEquals(expectedCustomerData.getSurnames(),actualVehicleInformation.getCustomerData().getSurnames());
        assertEquals(expectedPartDataList.size(),actualVehicleInformation.getPartsList().size());
        assertEquals(expectedPartDataList.get(0).getId(),actualVehicleInformation.getPartsList().get(0).getId());
        assertEquals(expectedPartDataList.get(0).getDescription(),actualVehicleInformation.getPartsList().get(0).getDescription());
        assertEquals(expectedPartDataList.get(1).getId(),actualVehicleInformation.getPartsList().get(1).getId());
        assertEquals(expectedPartDataList.get(1).getDescription(),actualVehicleInformation.getPartsList().get(1).getDescription());
        assertEquals(expectedPartDataList.get(2).getId(),actualVehicleInformation.getPartsList().get(2).getId());
        assertEquals(expectedPartDataList.get(2).getDescription(),actualVehicleInformation.getPartsList().get(2).getDescription());


        verify(vehicleCustomerDataService).getVehicleCustomerData(AN_EXISTENT_VIN);
        verify(customerDataRestServiceProxy).getCustomerData(CUSTOMER_ID);
        verify(vehicleDataRestServiceProxy).getVehicleData(AN_EXISTENT_VIN);
        verify(partDataRestServiceProxy).getPartDataList(AN_EXISTENT_VIN);


    }


}
