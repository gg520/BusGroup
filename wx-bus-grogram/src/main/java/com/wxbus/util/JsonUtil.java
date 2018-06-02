package com.wxbus.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.OutputStream;

/**
 * json数据处理
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json对象转换成字符串
     * @param object
     * @return
     */
    public static String stringify(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 读取配置文件
     * @param object
     * @param properties
     * @return
     */
    public static String stringify(Object object, String... properties) {

        try {
            return objectMapper
                    .writer(new SimpleFilterProvider().addFilter(
                            AnnotationUtils.getValue(
                                    AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(),
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                    .writeValueAsString(object);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 转换成输出流
     * @param out
     * @param object
     */
    public static void stringify(OutputStream out, Object object) {

        try {
            objectMapper.writeValue(out, object);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }
    }

    public static void stringify(OutputStream out, Object object, String... properties) {

        try {
            objectMapper
                    .writer(new SimpleFilterProvider().addFilter(
                            AnnotationUtils.getValue(
                                    AnnotationUtils.findAnnotation(object.getClass(), JsonFilter.class)).toString(),
                            SimpleBeanPropertyFilter.filterOutAllExcept(properties)))
                    .writeValue(out, object);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }
    }

    /**
     * 转换成对象类型
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> clazz) {

        if (json == null || json.length() == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
//            log.error(e.getMessage(), e);
        }

        return null;
    }

}
