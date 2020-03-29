package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = true)
    UserPrincipleDetailSerivce userPrincipleDetailSerivce ;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).
                withUser("kai").password("$2a$04$Q2Cq0k 57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).
                withUser("sena").password("$2a$04$Q2Cq0k57zf2Vs/n3JXwzmerql9RzElr.J7aQd3/Sq0fw/BdDFPAj.").roles("USER");
        auth.authenticationProvider(daoAuthenticationProvider());
//    auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).withUser("sena").password("123456").roles("USER");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        // Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn /user/**
        http.authorizeRequests().antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')");
        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/login")//
                .loginPage("/login")//
                .defaultSuccessUrl("/user")//
                .failureUrl("/login?message=error")//
                .usernameParameter("username")//-
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?message=logout").deleteCookies().invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/script/**", "/image/**", "/vendor/**", "/favicon.ico","/fonts/**," +
                        "/images/**","/Store/**","/styles/**","/s/**");
    }

    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userPrincipleDetailSerivce);
        return authenticationProvider;
    }

}
