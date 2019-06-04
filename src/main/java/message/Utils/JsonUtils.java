package message.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public final class JsonUtils {

    private static ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
    public static <T> T convertStringToObject(final String json, final Class<T> t) throws IOException {
        T model = null;
        if(!StringUtils.isEmpty(json)){
            model = getObjectMapper().readValue(json,t);
        }
        return model;
    }
}
