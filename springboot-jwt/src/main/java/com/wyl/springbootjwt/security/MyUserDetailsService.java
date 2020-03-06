package com.wyl.springbootjwt.security;

import com.wyl.springbootjwt.dao.RoleDao;
import com.wyl.springbootjwt.dao.RoleUserDao;
import com.wyl.springbootjwt.dao.UserDao;
import com.wyl.springbootjwt.model.Role;
import com.wyl.springbootjwt.model.RoleUser;
import com.wyl.springbootjwt.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description MyUserDetailsService 根据用户名获取数据库该用户信息，spring security在登录时自动调用
 * WebSecurityConfigurerAdapter会拿这里的User里的password与用户输入的对比验证
 * @Author YiLong Wu
 * @Date 2020/2/29 22:26
 * @Version 1.0.0
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "roleUserDao")
    private RoleUserDao roleUserDao;

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("*********username->{}",username);
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        // 根据用户名去查找对应的角色
        User user = userDao.findByUsername(username);
        log.info("******************{}",user);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username."));
        }
        RoleUser roleUser = roleUserDao.findByUserId(user.getId());
        Role role = roleDao.findById(roleUser.getRoleId()).get();
        GrantedAuthority au = new SimpleGrantedAuthority(role.getName());
        list.add(au);
        JwtUser JwtUser = new JwtUser(user.getUsername(),user.getPassword(),list);
        return JwtUser;
    }
}
