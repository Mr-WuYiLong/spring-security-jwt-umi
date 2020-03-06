package com.wyl.springbootjwt.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description RoleUser 角色用户表
 * @Author YiLong Wu
 * @Date 2020-03-05 22:00
 * @Version 1.0.0
 */
@Data
@Entity
@Table(name = "s_role_user")
public class RoleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "user_id")
    private Integer userId;
}
