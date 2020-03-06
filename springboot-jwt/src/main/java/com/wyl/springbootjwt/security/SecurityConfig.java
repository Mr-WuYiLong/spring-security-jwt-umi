package com.wyl.springbootjwt.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Description SecurityConfig 安全配置
 * @Author YiLong Wu
 * @Date 2020/2/28 14:51
 * @Version 1.0.0
 */
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)   // 开启方法权限控制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "tokenExceptionHandler")
    private TokenExceptionHandler tokenExceptionHandler;

    @Resource(name = "accessDeniedException")
    private AccessDeniedException accessDeniedException;

    @Resource(name = "jwtAuthenticationFilter")
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource(name = "myUserDetailsService")
    private MyUserDetailsService myUserDetailsService;

    @Resource(name = "myAuthenticationSuccessHandler")
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource(name = "myAuthenticationFailHandler")
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 忽略拦截
//        web.ignoring().antMatchers(HttpMethod.POST,"/api/login");
//    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // 移除ROLE_后缀
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    // 表示前端必须使用form表单的提交形式
                .formLogin()
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(myAuthenticationFailHandler)
                .and()
                // 让跨域配置被使用
                .cors()
                .and()
                .csrf().disable()
                // 添加异常处理
                .exceptionHandling()
                .authenticationEntryPoint(tokenExceptionHandler)
                .accessDeniedHandler(accessDeniedException)
                .and()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 拦截所有请求
                .authorizeRequests().anyRequest().authenticated();
        // 替换过滤器
        http.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//        // 禁用缓存
//        http.headers().cacheControl();
//        http.headers().frameOptions().disable();
    }
}
