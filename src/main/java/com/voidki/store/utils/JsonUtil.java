package com.voidki.store.utils;

import java.io.IOException;

import java.io.IOException;


import com.voidki.store.config.JacksonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * Jackson工具类
 **/
@Component
@ConditionalOnClass(JacksonConfig.class)
public class JsonUtil {


    public static ObjectMapper objectMapper;
    
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    //静态变量注入时，@Autowired注解只能在方法，不能在参数
    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    
    /**
     * Object转json字符串
     */
    public static <T> String toJson(T obj) {
        try {
            if (obj == null) {
                return null;
                
            }else if (obj instanceof String) {
                return (String) obj;
                
            }else {
                return objectMapper.writeValueAsString(obj);
            }
            
        } catch (Exception e) {
            return null;
        }
    }
    

    /**
     * Object转json字符串并格式化美化
     */
    public static <T> String toJsonPretty(T obj) {
        try {
            if (obj == null)
                return null;
            else if (obj instanceof String)
                return (String) obj;
            else
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    
    /**
     * json转object        
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            if (StringUtils.isEmpty(json) || clazz == null) {
                return null;
            }else if(clazz.equals(String.class)){
                return (T)json;
            }else{
                return objectMapper.readValue(json, clazz);
            }
        } catch (IOException e) {
            return null;
        }
    }
    

    /**
     * json转集合
     * @param <T>
     * @param json
     * @param typeReference 
     * <li>new TypeReference<List<User>>() {}</li>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String json, TypeReference<T> typeReference) {
        try {
            if (StringUtils.isEmpty(json) || typeReference == null) {
                return null;
                
            } else if (typeReference.getType().equals(String.class)) {
                return (T) json;
                
            } else {
                return objectMapper.readValue(json, typeReference);
            }
            
        } catch (IOException e) {
            return null;
        }
    }
    

    /**
     * string转object 用于转为集合对象
     * @param json Json字符串     
     * @param collectionClass 被转集合的类        
     * <p>List.class</p> 
     * @param elementClasses 被转集合中对象类型的类
     * <p>User.class</p> 
     */
    public static <T> T toBean(String json, Class<?> collectionClass, Class<?>... elementClasses) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return objectMapper.readValue(json, javaType);
            
        } catch (IOException e) {
            return null;
        }
    }
    
    
    
}