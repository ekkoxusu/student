package com.su.mapstruct.model.eo;

import java.time.LocalDateTime;

/**
 * @author xusu
 * @since 2021/4/28
 */
public class UserEO {
    private Long id;
    private String name;
    private LocalDateTime now;
    public Long getId() {
        return id;
    }

    public UserEO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public UserEO setNow(LocalDateTime now) {
        this.now = now;
        return this;
    }

    @Override
    public String toString() {
        return "UserEO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", now=" + now +
                '}';
    }
}
