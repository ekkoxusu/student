package com.su.student.mybatisplugins.model;

import com.su.student.mybatisplugins.enums.DelEnum;
import com.su.student.mybatisplugins.enums.TypeEnum;
import com.su.student.mybatisplugins.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 测试实体(MybatisPluginsStudent)实体类
 *
 * @author xusu
 * @since 2020-12-02 18:22:20
 */
@Data
@ApiModel(value="学生",description="学生")
public class MybatisPluginsStudent extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -10754942722979953L;

    /**
     * 名字
     */
    @ApiModelProperty("名字")
    private String name;
    
    /**
     * 类型[0-旧,1-新] 对内RPC建议不要使用枚举，防止双方序列化对象变更，导致新对象无法序列化问题
     */
    @ApiModelProperty("类型")
    private TypeEnum type;
    /**
     * 删除状态
     */
    @ApiModelProperty("删除")
    private DelEnum del;
    
}