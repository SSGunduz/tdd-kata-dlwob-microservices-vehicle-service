package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
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
@WebMvcTest(PartDataController.class)
@WithMockUser
public class PartDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartDataService partDataService;

    @Test
    public void Returns_500_when_service_error_exception() throws Exception {

        final String ANY_VIN="ANY_VIN";

        given(partDataService.getPartData(ANY_VIN)).willThrow(new RuntimeException("mock Exception"));

        mockMvc.perform(
                get(PartDataController.URL_MAPPING+"/{vinCode}",ANY_VIN)
            )
            .andExpect(status().isInternalServerError());

        verify(partDataService).getPartData(ANY_VIN);

    }
}