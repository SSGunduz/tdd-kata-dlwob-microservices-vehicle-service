package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.CustomerDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.controller.VehicleDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@RunWith(SpringRunner.class)
@RestClientTest(CustomerDataRestServiceProxy.class)
public class CustomerDataRestServiceProxyTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private CustomerDataRestServiceProxy proxy;

    /*
        1- If server returns 404, we get result = null
        2- If server returns a service error, an exception is thrown
        3- If server returns a client error, an exception is thrown
        4- If server returns valid data, we get back the correct data
     */

    @Test
    public void Returns_null_when_server_returns_404() {

        final String NON_EXISTENT_CUSTOMER_ID = "NON_EXISTENT_CUSTOMER_ID";

        server.expect(requestTo(CustomerDataController.URL_MAPPING + "/" + NON_EXISTENT_CUSTOMER_ID))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        CustomerData customerData = proxy.getCustomerData(NON_EXISTENT_CUSTOMER_ID);

        assertNull(customerData);

        server.verify();
    }

    @Test
    public void Throws_exception_when_server_error() {

        final String ANY_CUSTOMER_ID = "ANY_CUSTOMER_ID";

        server.expect(requestTo(CustomerDataController.URL_MAPPING + "/" + ANY_CUSTOMER_ID))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        try {
            proxy.getCustomerData(ANY_CUSTOMER_ID);
            fail("Should have thrown an exception");
        } catch (HttpServerErrorException e) {
            // Test is ok
        }

        server.verify();


    }

    @Test
    public void Throws_exception_when_client_error(){

        final String ANY_CUSTOMER_ID="ANY_CUSTOMER_ID";

        server.expect(requestTo(CustomerDataController.URL_MAPPING+"/"+ ANY_CUSTOMER_ID))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));

        try{
            proxy.getCustomerData(ANY_CUSTOMER_ID);
            fail("Should have thrown an exception");
        }catch (HttpClientErrorException e){
            // Test is ok
        }

        server.verify();

    }

}
