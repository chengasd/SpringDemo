package com.springboot.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: miachelyin
 * @Description: 比较字段是否有变更工具类
 * @Date: 2018/3/2
 * @Modified By:
 */
public class ClassFieldChangeUtil {

    public static <T> void contrastObj(T oldBean, T newBean) {
        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o1 == null || o2 == null) {
                    continue;
                }

                if (o1.toString().equals(o2.toString())) {
                    Method setMethod = pd.getWriteMethod();
                    setMethod.invoke(pojo1, new Object[]{null});
                    setMethod.invoke(pojo2, new Object[]{null});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Compare two object and return modified fields
     *
     * @param source source object
     * @param target target object
     * @return the modified fields and value after modify
     */
    public static Map<String, Object> getModifyContent(Object source, Object target) {
        Map<String, Object> modifies = new HashMap<>();
         /*
          process null problem, if all null means equal
          if only source is null means all modified
          if only target is null means nothing changed
         */
        if (null == source || null == target) {
            if (null == source && null == target) {
                return modifies;
            } else if (null == target) {
                return modifies;
            } else {
                return mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
                });
            }
        }
        // source and target must be same class type
        if (!Objects.equals(source.getClass().getName(), target.getClass().getName())) {
            throw new ClassCastException("source and target are not same class type");
        }
        Map<String, Object> sourceMap = mapper.convertValue(source, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> targetMap = mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
        });
        sourceMap.forEach((k, v) -> {
            Object targetValue = targetMap.get(k);
            if (!Objects.equals(v, targetValue)) {
                modifies.put(k, targetValue);
            }
        });
        return modifies;
    }

    /**
     * Compare two object and return modified fields which contain in comparedProperties
     *
     * @param source             ource object
     * @param target             target object
     * @param comparedProperties the fields need to be compare
     * @return the modified fields and value after modify
     */
    public static Map<String, Object> getModifyContent(Object source, Object target, Map<String, String> comparedProperties) {
        Map<String, Object> modifies = new HashMap<>();
        if (null == source || null == target) {
            if (null == source && null == target) {
                return modifies;
            } else if (null == target) {
                return modifies;
            } else {
                return mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
                });
            }
        }
        if (!Objects.equals(source.getClass().getName(), target.getClass().getName())) {
            throw new ClassCastException("source and target are not same class type");
        }
        Map<String, Object> sourceMap = mapper.convertValue(source, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> targetMap = mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
        });
        sourceMap.forEach((k, v) -> {
            if (comparedProperties != null && !comparedProperties.containsKey(k)) {
                return;
            }
            Object targetValue = targetMap.get(k);
            if (!Objects.equals(v, targetValue)) {
                modifies.put(k, targetValue);
            }
        });
        return modifies;
    }

    /**
     * Compare two object and return if equal
     *
     * @param source source object
     * @param target target object
     * @return true-equal
     */
    public static boolean isEuqal(Object source, Object target) {
        if (null == source || null == target) {
            return false;
        }
        if (!Objects.equals(source.getClass().getName(), target.getClass().getName())) {
            return false;
        }
        Map<String, Object> sourceMap = mapper.convertValue(source, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> targetMap = mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
        });
        return Objects.equals(sourceMap, targetMap);
    }

    /**
     * Compare two object and return if equal
     *
     * @param source             source object
     * @param target             target object
     * @param comparedProperties only compare fields in this map
     * @return rue-equal
     */
    public static boolean isEuqal(Object source, Object target, Map<String, String> comparedProperties) {
        if (null == source || null == target) {
            return null == source && null == target;
        }
        if (!Objects.equals(source.getClass().getName(), target.getClass().getName())) {
            return false;
        }
        Map<String, Object> sourceMap = mapper.convertValue(source, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> targetMap = mapper.convertValue(target, new TypeReference<Map<String, Object>>() {
        });
        for (String k : sourceMap.keySet()) {
            if (comparedProperties != null && !comparedProperties.containsKey(k)) {
                continue;
            }
            Object v = sourceMap.get(k);
            Object targetValue = targetMap.get(k);
            if (!Objects.equals(v, targetValue)) {
                return false;
            }
        }
        return true;
    }
}
