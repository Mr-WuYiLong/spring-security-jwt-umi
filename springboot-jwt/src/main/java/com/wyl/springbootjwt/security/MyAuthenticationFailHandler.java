package com.wyl.springbootjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyl.springbootjwt.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description MyAuthenticationFailHandler 登录失败处理类
 * @Author YiLong Wu
 * @Date 2020/3/1 13:18
 * @Version 1.0.0
 */
@Component
@Slf4j
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        log.info("*****************登录失败*************");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        Response<Object> response = new Response<>();
        response.setCode(-1);
        response.setMessage("用户名或密码错误");
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(response));
    }
}
