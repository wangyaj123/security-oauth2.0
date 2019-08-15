package com.wyj.oauth.security.config;

import com.wyj.oauth.security.service.impl.BaseUserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring-Security配置
 * @author wangyajing
 * @date 2019-08-14
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 通过自定义userDetailsService 来实现查询数据库，手机，二维码等多种验证方式
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        //采用自定义的实现UserDetailsService接口的类
        return new BaseUserDetailServiceImpl();
        /*
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = "{bcrypt}"+bCryptPasswordEncoder.encode("123456");
        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
        finalPassword = "{noop}123456";
        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
        return manager;
         */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/*").permitAll();
                */
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.logout().permitAll();
        http.authorizeRequests().antMatchers("/oauth/authorize").permitAll();
    }

    /**
     * 用户验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * Spring Boot 2 配置，这里要bean 注入
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
