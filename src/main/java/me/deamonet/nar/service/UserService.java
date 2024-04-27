package me.deamonet.nar.service;

import me.deamonet.nar.entity.User;
import me.deamonet.nar.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    final
    UserMapper userMapper;
    final
    BCryptPasswordEncoder encoder;

    public UserService(UserMapper userMapper, BCryptPasswordEncoder encoder) {
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public User findUserById(Integer id) {
        return userMapper.select(id);
    }

    public User loadUserByUsername(String username){
        return userMapper.selectUserByUsername(username);  //从数据库根据用户名获取密码
    }

    @Transactional
    public Integer register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Transactional
    public Integer edit(User user) {
        return userMapper.update(user);
    }

    @Transactional
    public Integer delete(Integer id) {
        return userMapper.delete(id);
    }

    @Transactional
    public Integer resetPassword(int id, String password){
        return userMapper.resetPassword(id, encoder.encode(password));
    }
}

