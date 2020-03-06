package com.wyl.springbootjwt.dao;

import com.wyl.springbootjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description UserDao
 * @Author YiLong Wu
 * @Date 2020/2/28 16:04
 * @Version 1.0.0
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 根据名字来查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
