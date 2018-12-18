package com.example.jsspringzuul.uitl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class JsonUtil {
    public static ObjectMapper mapper;
/*    static {
        dao = new ObjectMapper();
        dao.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
        dao.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    }*/

    public JsonUtil(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        mapper = jackson2ObjectMapperBuilder.build();
    }

    public static String serializeToString(Object object) {
        return serializeToString(object,false);
    }

    public static String serializeToString(Object object,Boolean rootValueState) {
        SerializationConfig serializationConfig = mapper.getSerializationConfig();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,rootValueState);
        if (object != null) {
            try {
                return mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } finally {
                mapper.setConfig(serializationConfig);
            }
        }
        return "";
    }

    public static byte[] serializeToBytes(Object object) {
        if (object != null) {
            try {
                return mapper.writeValueAsBytes(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反序列化Json数据
     * @param jsonData Json数据字符串
     * @param valueType 反序列化的类型
     * @param rootValueState 是否解析Json root name
     * @param <T>
     * @return 反序列化后的POJO
     */
    public static <T> T deserialize(String jsonData,Class<T> valueType,Boolean rootValueState) {
        if (StringUtils.isEmpty(jsonData) || rootValueState == null) {
            return null;
        }
        DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,rootValueState);
        try {
            return mapper.readValue(jsonData, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mapper.setConfig(deserializationConfig);
        }
        return null;
    }

    /**
     * 反序列化Json数据,默认解析Json root name
     * @param jsonData Json数据字符串
     * @param valueType 反序列化的类型
     * @param <T>
     * @return 反序列化后的POJO
     */
    public static <T> T deserialize(String jsonData,Class<T> valueType) {
        return deserialize(jsonData, valueType, true);
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 用Json数据中的key获取对应的value
     * @param jsonString json数据字符串
     * @param field 需要取值的字段
     * @param rootValueState 是否解析Json root name
     * @return 字段对应的值
     */
    public static String getValue(String jsonString,String field,Boolean rootValueState) {
        JsonNode node = getJsonNode(jsonString,field,rootValueState);
        return node == null ? "" : node.toString();
    }

    /**
     * 用Json数据中的key获取对应的value, 默认解析Json root name
     * @param jsonString json数据字符串
     * @param field 需要取值的字段
     * @return 字段对应的值
     */
    public static String getValue(String jsonString,String field) {
        return getValue(jsonString,field,true);
    }

    /**
     * 用Json数据中的key获取对应的value
     * @param jsonString json数据字符串
     * @param field 需要取值的字段
     * @param rootValueState 是否解析Json root name
     * @return 字段对应的值
     */
    public static JsonNode getJsonNode(String jsonString, String field, Boolean rootValueState) {
        if (StringUtils.isEmpty(jsonString) || StringUtils.isEmpty(field) || rootValueState == null) {
            return null;
        }
        // 准备工作 传入vo请参照第一篇里面的实体。此处不再重新贴上代码 浪费大家时间
        JsonNode node = null;// 这里的JsonNode和XML里面的Node很像
        // 默认的反序列化配置
        DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,rootValueState);
        try {
            node = mapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            mapper.setConfig(deserializationConfig);
        }
        return node.get(field) == null ? null : node.get(field);
    }

}
