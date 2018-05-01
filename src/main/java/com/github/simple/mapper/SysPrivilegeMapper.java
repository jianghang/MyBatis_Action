package com.github.simple.mapper;

import com.github.model.SysPrivilege;

public interface SysPrivilegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPrivilege record);

    int insertSelective(SysPrivilege record);

    SysPrivilege selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPrivilege record);

    int updateByPrimaryKey(SysPrivilege record);
}