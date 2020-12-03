package com.su.student.mybatisplugins.util;

import com.su.student.mybatisplugins.enums.BaseEnum;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @author xusu
 * @since 2020/12/2
 */
public class EnumUtil {

    /**
     * 获取枚举来自于code
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E extends Enum<E> & BaseEnum> E getEnmu(Integer value, Class<E> clazz) {
        Objects.requireNonNull(value);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> e.getValue() == value).findFirst().orElse(null);
    }

    /**
     * 获取枚举来自于name
     * @param desc
     * @param clazz
     * @param <E>
     * @return
     */
    public static  <E extends Enum<E> & BaseEnum> E getEnmu(String desc, Class<E> clazz) {
        Objects.requireNonNull(desc);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter(e -> e.getDesc().equals(desc)).findFirst().orElse(null);
    }

}
