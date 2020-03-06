package com.wyl.springbootjwt.dao;

import com.wyl.springbootjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description RoleDao
 * @Author YiLong Wu
 * @Date 2020-03-05 22:26
 * @Version 1.0.0
 */
@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {

}
