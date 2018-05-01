package com.github.simple.mapper;

import com.github.model.SysRole;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Select;

@CacheNamespaceRef(SysRoleMapper.class)
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    @Select({"select \n" +
            "    id, role_name, enabled, create_by, create_time\n" +
            "    from sys_role\n" +
            "    where id = #{id,jdbcType=BIGINT}"})
    SysRole selectById(Long id);
}