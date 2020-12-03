package com.su.student.mybatisplugins.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 测试枚举操作
 * @author xusu
 * @since 2020/12/2
 */
public enum DelEnum implements IEnum<Integer>,BaseEnum {
    /**
     * 正常
     */
    NORMAL("正常",0),
    /**
     * 删除
     */
    DEL("删除状态",1)
    ;

    private final String desc;
    private final Integer value;

    DelEnum(String desc, Integer value) {
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
