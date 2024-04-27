package me.deamonet.nar.service.implement;

import lombok.extern.slf4j.Slf4j;
import me.deamonet.nar.entity.User;
import me.deamonet.nar.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class UserAuthenticationServiceImplement implements UserDetailsService {

    @Resource
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.info("登录用户[{}]没注册!", username);
            throw new UsernameNotFoundException("登录用户["+username + "]没注册!");
        }
        assert Objects.equals(username, user.getUsername());
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(getAuthority())
                .roles("user")
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
