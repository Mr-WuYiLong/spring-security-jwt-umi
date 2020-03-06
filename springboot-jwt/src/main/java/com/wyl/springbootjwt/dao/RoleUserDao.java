package com.wyl.springbootjwt.dao;

import com.wyl.springbootjwt.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description RoleUserDao
 * @Author YiLong Wu
 * @Date 2020-03-05 22:21
 * @Version 1.0.0
 */
@Repository
public interface RoleUserDao extends JpaRepository<RoleUser,Integer> {

    /**
     * 根据用户的id查找对应的角色
     * @param userId
     * @return
     */
    RoleUser findByUserId(Integer userId);
}
