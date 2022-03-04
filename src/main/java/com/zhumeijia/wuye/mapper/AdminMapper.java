package com.zhumeijia.wuye.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.zhumeijia.wuye.bean.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {
    public Admin findAdmin(@Param("username") String username,@Param("password") String password);
    public Integer updatePass(@Param("id") int id,@Param("newPsw") String newPsw);

    List<Admin> findAdminById(Integer createBy);
}
