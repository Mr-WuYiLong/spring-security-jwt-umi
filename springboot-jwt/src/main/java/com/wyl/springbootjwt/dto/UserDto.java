package com.wyl.springbootjwt.dto;

import lombok.Data;

/**
 * @Description User 用户传输对象
 * @Author YiLong Wu
 * @Date 2020/2/29 13:34
 * @Version 1.0.0
 */
@Data
public class UserDto {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
