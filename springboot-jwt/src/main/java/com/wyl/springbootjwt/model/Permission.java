package com.wyl.springbootjwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description Permission 权限表
 * @Author YiLong Wu
 * @Date 2020-03-05 21:48
 * @Version 1.0.0
 */
@Data
@Entity
@Table(name = "s_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code",length = 20)
    private String code;

    @Column(name = "action",length = 100)
    private String action;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "creator",length = 20)
    private String creator;

    @Column(name = "updater",length = 20)
    private String updater;
}
