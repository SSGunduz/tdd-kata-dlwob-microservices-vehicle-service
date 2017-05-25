package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.CustomerDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers=CustomerDataController.class)
@WithMockUser
public class CustomerDataControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDataService customerDataService;

    @Test
    public void Returns_500_when_service_error() throws Exception{

        final String ANY_CUSTOMER_ID="ANY_CUSTOMER_ID";

        given(customerDataService.getCustomerData(ANY_CUSTOMER_ID)).willThrow(new RuntimeException("Mock Exception"));

        mockMvc
                .perform(
                        get(CustomerDataController.URL_MAPPING+"/{customerId}",ANY_CUSTOMER_ID)
                    )
                .andExpect(status().isInternalServerError())
                ;

        verify(customerDataService).getCustomerData(ANY_CUSTOMER_ID);
    }

}
