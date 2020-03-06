package com.wyl.springbootjwt.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Description RolePermission 角色权限表
 * @Author YiLong Wu
 * @Date 2020-03-05 21:52
 * @Version 1.0.0
 */

@Data
@Entity
@Table(name = "s_role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;
}
