package com.wyl.springbootjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyl.springbootjwt.common.Response;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description AccessDeniedException
 * @Author YiLong Wu
 * @Date 2020/2/28 15:22
 * @Version 1.0.0
 */
@Component
public class AccessDeniedException implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.access.AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        Response<Object> response = new Response<>();
        response.setCode(403);
        response.setMessage("用户得到授权，但是访问是被禁止的");
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(response));
    }
}
