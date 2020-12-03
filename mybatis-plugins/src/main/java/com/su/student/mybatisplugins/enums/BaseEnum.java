package com.su.student.mybatisplugins.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author xusu
 * @since 2020/12/2
 */
public interface BaseEnum {
    /**
     * key
     * @return
     */
    String getDesc();

    /**
     * value
     * @return
     */
    Integer getValue();


}
