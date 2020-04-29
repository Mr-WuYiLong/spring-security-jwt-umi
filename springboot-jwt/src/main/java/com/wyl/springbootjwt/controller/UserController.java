package com.wyl.springbootjwt.controller;

import com.wyl.springbootjwt.common.Response;
import com.wyl.springbootjwt.dao.UserDao;
import com.wyl.springbootjwt.dto.UserDto;
import com.wyl.springbootjwt.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Description UserController
 * @Author YiLong Wu
 * @Date 2020/3/1 14:44
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userDao")
    private UserDao userDao;

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN')")
    @PostMapping("/add")
    public Response<User> addUser(@RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt()));
        User save = userDao.save(user);
        return new Response(200,"操作成功",save);
    }

    @GetMapping("/page")
    public Response<User> pageUser(@RequestParam(value = "current",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable =PageRequest.of(pageNum - 1, pageSize, sort);
        Page<User> userList = userDao.findAll(pageable);
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("current",pageNum);
        map1.put("pageSize",pageSize);
        map1.put("total",userList.getTotalElements());
        map.put("data",userList.getContent());
        map.put("pagination",map1);
        return new Response(200,null,map) ;
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/deleteById")
    public Response<User> deleteUser(@RequestParam(value = "id") Integer id) {
        userDao.deleteById(id);
        return new Response(200,"操作成功",null);
    }
}
