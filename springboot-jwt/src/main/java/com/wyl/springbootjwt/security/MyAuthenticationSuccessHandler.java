package com.wyl.springbootjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyl.springbootjwt.common.Response;
import com.wyl.springbootjwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description MyAuthenticationSuccessHandler 登录成功处理类
 * @Author YiLong Wu
 * @Date 2020/3/1 13:00
 * @Version 1.0.0
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        chain.doFilter();
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
       log.info("****************登录成功************");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        //从authentication中获取用户信息
        JwtUser userDetail= (JwtUser)authentication.getPrincipal();
        // 生成token
        String token = JwtUtil.generateToken(userDetail.getUsername());
        Response<Object> response = new Response<>();
        response.setCode(200);
        response.setMessage("登录成功");
        response.setData(token);
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(response));
    }
}
