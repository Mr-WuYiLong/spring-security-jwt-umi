package com.wyl.springbootjwt.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description s_role 角色表
 * @Author YiLong Wu
 * @Date 2020-03-05 21:39
 * @Version 1.0.0
 */
@Data
@Entity
@Table(name = "s_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name",length = 20)
    private String name;

    @Column(name = "nickname",length = 20)
    private String nickname;

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
