package com.su.cache.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 库存(Stock)实体类
 *
 * @author xusu
 * @since 2020-12-05 15:31:19
 */
@Data
public class Stock implements Serializable {
    private static final long serialVersionUID = 600430865865361134L;
    private Long id;
    private Integer num;
    private Long productId;

}