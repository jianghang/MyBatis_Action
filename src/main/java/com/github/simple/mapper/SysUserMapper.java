package com.github.simple.mapper;

import com.github.model.SysRole;
import com.github.model.SysUser;
import com.github.model.SysUserWithBLOBs;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@CacheNamespace
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserWithBLOBs record);

    int insert2(SysUserWithBLOBs sysUserWithBLOBs);

    int insert3(SysUserWithBLOBs sysUserWithBLOBs);

    int insertList(List<SysUserWithBLOBs> sysUserWithBLOBsList);

    int insertSelective(SysUserWithBLOBs record);

    SysUserWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysUserWithBLOBs record);

    int updateByPrimaryKey(SysUser record);

    int updateByMap(Map<String,Object> map);

    List<SysUser> selectAll();

    List<Map<String,Object>> selectUserById(Integer id);

    List<SysRole> selectRolesByUserId(Integer id);

    List<SysUser> selectByUser(SysUser sysUser);

    List<SysUserWithBLOBs> selectByIdList(List<Integer> idList);

    List<SysUser> selectUserAndRoleById(Integer id);

    List<SysUser> selectUserAll(RowBounds rowBounds);

    List<Map<String,Object>> selectSomeThing(@Param("sql") String sql);
}