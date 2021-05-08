package com.su.springdoc.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author xusu
 * @since 2021/4/26
 */
public class User {

    @NotNull(message = "id必须有值")
    @Max(message = "最大值为10",value=10)
    @Min(message = "最小值为0",value=0)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
