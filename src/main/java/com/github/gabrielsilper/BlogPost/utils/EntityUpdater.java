package com.github.gabrielsilper.BlogPost.utils;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Objects;

public class EntityUpdater {
    private static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(PropertyDescriptor::getName)
                .filter(name -> {
                    try {
                        return Objects.requireNonNull(BeanUtils.getPropertyDescriptor(source.getClass(), name)).getReadMethod().invoke(source) == null;
                    } catch (Exception e) {
                        return true;
                    }
                })
                .toArray(String[]::new);
    }

    public static void mergeNotNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
}
