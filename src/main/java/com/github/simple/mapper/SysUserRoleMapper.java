package com.github.simple.mapper;

import com.github.model.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}