package me.deamonet.nar.configuration;

import me.deamonet.nar.handler.*;
import me.deamonet.nar.service.UserAuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final
    UserAuthenticationService userAuthenticationService;
    final
    BCryptPasswordEncoder bCryptPasswordEncoder;
    final
    EntryPointHandler entryPointHandler;    //未登录
    final
    SuccessHandler successHandler;    //登录成功
    final
    FailureHandler failureHandler;    //登录失败
    final
    LogoutHandler logoutHandler;      //注销
    final
    DenyHandler denyHandler;      //无权访问
    final
    SessionExpireHandler sessionExpireHandler;    //检测异地登录
    final
    CustomAuthenticationProvider customAuthenticationProvider;      //自定义认证逻辑处理

    public SecurityConfiguration(UserAuthenticationService userAuthenticationService, BCryptPasswordEncoder bCryptPasswordEncoder, EntryPointHandler entryPointHandler, SuccessHandler successHandler, FailureHandler failureHandler, LogoutHandler logoutHandler, DenyHandler denyHandler, SessionExpireHandler sessionExpireHandler, CustomAuthenticationProvider customAuthenticationProvider) {
        this.userAuthenticationService = userAuthenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.entryPointHandler = entryPointHandler;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.logoutHandler = logoutHandler;
        this.denyHandler = denyHandler;
        this.sessionExpireHandler = sessionExpireHandler;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:5500"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(30000L);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cors()解决跨域问题，csrf()会与restful风格冲突，默认spring security是开启的，所以要disable()关闭一下
        http.cors().and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/user/{userId}/**").access("@httpPermissionHandler.checkUserId(authentication,#userId)")
                .antMatchers("/register").permitAll()

                .and()
                .formLogin()  //开启登录
                .permitAll()  //允许所有人访问
                .successHandler(successHandler) // 登录成功逻辑处理
                .failureHandler(failureHandler) // 登录失败逻辑处理

                .and()
                .logout()   //开启注销
                .permitAll()    //允许所有人访问
                .logoutSuccessHandler(logoutHandler) //注销逻辑处理
                .deleteCookies("JSESSIONID")    //删除cookie

                .and()
                .exceptionHandling()
                .accessDeniedHandler(denyHandler)    //权限不足的时候的逻辑处理
                .authenticationEntryPoint(entryPointHandler)  //未登录是的逻辑处理

                .and()
                .sessionManagement()
                .maximumSessions(1)     //最多只能一个用户登录一个账号
                .expiredSessionStrategy(sessionExpireHandler)  //异地登录的逻辑处理
        ;
    }
}
