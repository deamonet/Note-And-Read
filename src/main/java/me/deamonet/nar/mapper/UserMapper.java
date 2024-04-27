package me.deamonet.nar.mapper;

import me.deamonet.nar.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User select(Integer id);
    User selectUserByUsername(String username);
    Integer insert(User user);
    Integer update(User user);
    Integer delete(Integer id);
    Integer resetPassword(@Param("id") Integer id, @Param("password") String password);
}
