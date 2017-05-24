package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
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

    protected AbstractMockServiceImpl(TypeReference<T> dtoTypeReference) {
        readJsonFile(dtoTypeReference);
    }


    protected T dtoObject;

    private void readJsonFile(Object dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);

            if (dtoClass instanceof TypeReference) {
                dtoObject = objectMapper.readValue(resourceInputStream, (TypeReference<T>) dtoClass);
            }
            else {
                dtoObject = objectMapper.readValue(resourceInputStream, (Class<T>) dtoClass);
            }

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
