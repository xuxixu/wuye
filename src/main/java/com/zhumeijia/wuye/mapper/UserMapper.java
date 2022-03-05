package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    public int getCountAll();
    public List<User> getAllUser(@Param("page") int page,@Param("limit") int limit);
    public int addUser(User user);
    public int updateUser(User user);
    public int delUser(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<User> findUser(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<User> getAllUsers();

    List<User> findUserById(int id);

    User loginByPassword(String phone, String password);

    int updatePass(Integer id, String newPsw);

    User getUserById(Integer id);

    int findUserByPhone(String phone);
}
