package com.wyl.springbootjwt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Response 公共的响应类
 * @Author YiLong Wu
 * @Date 2020/2/28 15:02
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private Integer code;

    private String message;

    private T data;

}
