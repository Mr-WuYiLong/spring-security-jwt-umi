package com.wyl.springbootjwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description User 用户表
 * @Author YiLong Wu
 * @Date 2020/2/28 13:44
 * @Version 1.0.0
 */
@Data
@Entity
@Table(name = "s_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username",length = 20)
    private String username;

    @Column(name = "password",length = 100)
    private String password;

    @Column(name="status")
    private Boolean status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "creator",length = 20)
    private String creator;

    @Column(name = "updater",length = 20)
    private String updater;

}
