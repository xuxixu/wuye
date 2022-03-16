package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    public int getCountAll();
    public List<Role> getAllRole(@Param("page") int page,@Param("limit") int limit);
    public int addRole(Role role);
    public int updateRole(Role role);
    public int delRole(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Role> findRole(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Role> getAllRoles();

    Role findRoleById(Integer pid);
}
