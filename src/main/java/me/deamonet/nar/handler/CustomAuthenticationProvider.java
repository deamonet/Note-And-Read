package me.deamonet.nar.handler;

import me.deamonet.nar.service.UserAuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final
    UserAuthenticationService userAuthenticationService;
    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserAuthenticationService userAuthenticationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAuthenticationService = userAuthenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = authentication.getName();     //获取用户名
        String password = (String) authentication.getCredentials();  //获取密码
        UserDetails userDetails = userAuthenticationService.loadUserByUsername(account);
        boolean checkPassword = bCryptPasswordEncoder.matches(password,userDetails.getPassword());
        if(!checkPassword){
            throw new BadCredentialsException("密码不正确，请重新登录!");
        }
        return new UsernamePasswordAuthenticationToken(account,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
