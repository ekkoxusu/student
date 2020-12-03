package com.su.student.mybatisplugins.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 测试枚举操作
 * @author xusu
 * @since 2020/12/2
 */
public enum TypeEnum implements IEnum<Integer>,BaseEnum {
    /**
     * 老
     */
    OLD("老",1),
    /**
     * 新
     */
   NEW("新",2)
    ;

    private final String desc;
    /**
     *  继承IEnum或者使用该注解
     *  @EnumValue
     */
    private final Integer value;

    TypeEnum(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
