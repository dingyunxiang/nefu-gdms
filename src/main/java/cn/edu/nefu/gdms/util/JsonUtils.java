package cn.edu.nefu.gdms.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }

    public static byte[] toBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public static <T> T toObject(byte[] data, Class<T> clazz) throws IOException {
        return mapper.readValue(data, clazz);
    }

    public static <T> T toObject(String data, Class<T> clazz) throws IOException {
        return mapper.readValue(data, clazz);
    }

    public static <T> List<T> toArray(String data, Class<T> clazz) throws IOException {
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(data, type);
    }

    public static <T> List<T> toArray(byte[] data, Class<T> clazz) throws IOException {
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(data, type);
    }
}
