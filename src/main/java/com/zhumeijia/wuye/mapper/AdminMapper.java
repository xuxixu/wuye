package com.zhumeijia.wuye.mapper;

import com.zhumeijia.wuye.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.zhumeijia.wuye.bean.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {
    public Admin findAdmin(@Param("username") String username,@Param("password") String password);
    public Integer updatePass(@Param("id") int id,@Param("newPsw") String newPsw);

    List<Admin> findAdminById(Integer createBy);
    public int getCountAll();
    public List<Admin> getAllAdmin(@Param("page") int page, @Param("limit") int limit);
    public int addAdmin(Admin role);
    public int updateAdmin(Admin role);
    public int delAdmin(@Param("id") int id);
    public int getCountByName(@Param("name")String name);
    public List<Admin> findAdminByName(@Param("page") int page,@Param("limit") int limit,@Param("name") String name);
    public List<Admin> getAllAdmins();
}
