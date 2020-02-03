package com.tistory.byrage.springboot.webserver.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBindUtils {

    public static Object bind(ServletRequest req, Class<?> type, String value) {
        if (isPrimitive(type)) {
            return createValueObject(type, value);
        }

        try {
            Object o = type.newInstance();
            List<Method> setters = findSetters(type.getMethods());
            for (Method setter : setters) {
                String fieldName = extractFieldFromSetter(setter);
                setter.invoke(o, createValueObject(setter.getParameterTypes()[0], req.getParameter(fieldName)));
            }
            return o;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("[DataBindUtils] {} occurred exception.", e.getClass().getSimpleName(), e);
            return null;
        }
    }

    private static String extractFieldFromSetter(Method setter) {
        return setter.getName().replaceAll("set", "")
                .toLowerCase();
    }

    private static Object createValueObject(Class<?> type, String value) {
        if (type.getName().equals("int") || type == Integer.class) {
            return new Integer(value);
        } else if (type.getName().equals("float") || type == Float.class) {
            return new Float(value);
        } else if (type.getName().equals("double") || type == Double.class) {
            return new Double(value);
        } else if (type.getName().equals("long") || type == Long.class) {
            return new Long(value);
        } else if (type.getName().equals("boolean") || type == Boolean.class) {
            return Boolean.valueOf(value);
        } else {
            return value;
        }
    }

    private static List<Method> findSetters(Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set"))
                .collect(Collectors.toList());
    }

    private static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive() ||
                type.equals(java.lang.Boolean.class) ||
                type.equals(java.lang.Character.class) ||
                type.equals(java.lang.Byte.class) ||
                type.equals(java.lang.Short.class) ||
                type.equals(java.lang.Integer.class) ||
                type.equals(java.lang.Long.class) ||
                type.equals(java.lang.Float.class) ||
                type.equals(java.lang.Double.class) ||
                type.equals(java.lang.Void.class);
    }
}
