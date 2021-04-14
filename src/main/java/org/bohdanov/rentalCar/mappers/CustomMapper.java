package org.bohdanov.rentalCar.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Map;

public class CustomMapper {
    public static String getJsonWithRootName(String rootName, Map<?, ?> collection) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer().withRootName(rootName);
        return writer.writeValueAsString(collection);
    }
}
