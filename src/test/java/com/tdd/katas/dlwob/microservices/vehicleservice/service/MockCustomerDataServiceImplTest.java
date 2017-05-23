package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockCustomerDataServiceImplTest {


    @Test
    public void Returns_Null_For_Non_Existent_Customer_Id(){

        MockCustomerDataServiceImpl service=new MockCustomerDataServiceImpl();

        CustomerData actualCustomerData=service.getCustomerData("Non existent customer id");
        assertNull(actualCustomerData);
    }

    @Test
    public void Returns_Valid_Data_For_An_Existent_Customer_Id(){
        MockCustomerDataServiceImpl service=new MockCustomerDataServiceImpl();

        CustomerData actualCustomerData=service.getCustomerData("sample-customer-id");

        assertNotNull(actualCustomerData);
        assertEquals("sample-customer-id",actualCustomerData.getId());
        assertEquals("Sergio",actualCustomerData.getName());
        assertEquals("Osuna Medina",actualCustomerData.getName());
    }

}
