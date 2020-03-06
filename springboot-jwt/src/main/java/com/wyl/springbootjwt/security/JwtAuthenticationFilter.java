package com.wyl.springbootjwt.security;

import com.wyl.springbootjwt.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description JwtAuthenticationFilter
 * @Author YiLong Wu
 * @Date 2020/2/28 15:45
 * @Version 1.0.0
 */
@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    @Resource(name = "myUserDetailsService")
    MyUserDetailsService myUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("***************进入过滤器**********");
        String authHeader = request.getHeader("Authorization");
        //获取token，并且解析token，如果解析成功，则放入 SecurityContext
        if (authHeader != null  && authHeader.startsWith("Bearer ")) {
            try {
                String username = JwtUtil.validateToken(authHeader);
                UserDetails UserDetails = myUserDetailsService.loadUserByUsername(username);
                //这里只要告诉springsecurity权限即可，账户密码就不用提供验证了，这里我们把UserDetails传给springsecurity，以便以后我们获取当前登录用户
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(UserDetails, null, UserDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                logger.info("解析失败，可能是伪造的或token失效了");
            }
        }
        filterChain.doFilter(request, response);
    }
}
