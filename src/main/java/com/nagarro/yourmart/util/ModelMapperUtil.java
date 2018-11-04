package com.nagarro.yourmart.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.List;


public class ModelMapperUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
    private static TypeFactory typeFactory = objectMapper.getTypeFactory();

    public static <T1> List<T1> convertModelList(List<? extends Object> sourceClass, Class<T1> destinationClass) {
        try {
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, destinationClass);
            return objectMapper.convertValue(sourceClass, collectionType);
        } catch (Exception exp) {
            return null;
        }
    }

    public static <T> T convertModel(Object sourceClass, Class<T> destinationClass) {
        try {
            return objectMapper.convertValue(sourceClass, destinationClass);
        } catch (Exception exp) {
            exp.printStackTrace();
        	return null;
        }
    }

}
