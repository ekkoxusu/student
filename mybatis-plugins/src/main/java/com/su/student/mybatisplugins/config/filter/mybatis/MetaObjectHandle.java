package com.su.student.mybatisplugins.config.filter.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 新增修改的时候自动处理
 * @author xusu
 * @since 2020/12/2
 */
@Component
public class MetaObjectHandle implements MetaObjectHandler {


    private String CREATE_TIME = "createTime";

    private String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (getFieldValByName(CREATE_TIME,metaObject) == null) {
            metaObject.setValue(CREATE_TIME, LocalDateTime.now());
        }
        updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue(UPDATE_TIME, LocalDateTime.now());
    }
}
