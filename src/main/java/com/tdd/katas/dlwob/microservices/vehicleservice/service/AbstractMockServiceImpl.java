package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class AbstractMockServiceImpl<T>  {

    protected AbstractMockServiceImpl(Class<T> dtoClass) {
        readJsonFile(dtoClass);
    }

    protected T dtoObject;

    private void readJsonFile(Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);
            dtoObject = objectMapper.readValue(resourceInputStream, dtoClass);
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error reading from JSON file", e);
        } finally {
            if (resourceInputStream!=null) {
                try {
                    resourceInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
