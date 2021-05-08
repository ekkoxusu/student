package com.su.mapstruct.model.dto;

/**
 * @author xusu
 * @since 2021/4/28
 */
public class UserDTO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
