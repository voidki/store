package com.voidki.store.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @projectName: store
 * @package: com.voidki.store.utils
 * @className: MyCache
 * @author: voidki
 * @description:
 * @date: 2023/2/1 17:13
 */
public class MyCache{
    public final static Map<String,Object> cache = new HashMap<>();
    public static void put(String key,Object value){
        cache.put(key,value);
    }
    public static void remove(String key){
        if(!Objects.isNull(cache.get(key))){
            cache.remove(key);
        }
    }
    public static Object get(String key){
        return cache.get(key);
    }
}
