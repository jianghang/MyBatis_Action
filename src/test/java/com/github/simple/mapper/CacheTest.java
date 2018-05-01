package com.github.simple.mapper;

import com.alibaba.fastjson.JSON;
import com.github.model.SysRole;
import com.github.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jianghang on 2018/5/1.
 */
public class CacheTest extends BaseMapperTest{

    @Test
    public void testL1Cache(){
        SqlSession sqlSession = getSqlSession();
        SysUser sysUser = null;
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            sysUser = sysUserMapper.selectByPrimaryKey(1);
            System.out.println(JSON.toJSONString(sysUser));
            sysUser.setUserName("New Name");

            SysUser sysUser1 = sysUserMapper.selectByPrimaryKey(1);
            System.out.println(JSON.toJSONString(sysUser1));

            Assert.assertEquals("New Name",sysUser1.getUserName());
            Assert.assertEquals(sysUser,sysUser1);
        }finally {
            sqlSession.close();
        }

        System.out.println("开启新的sqlSession");

        sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser1 = sysUserMapper.selectByPrimaryKey(1);
            System.out.println(JSON.toJSONString(sysUser1));
            Assert.assertNotEquals("New Name",sysUser1.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache(){
        SqlSession sqlSession = getSqlSession();
        SysRole role1 = null;
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            role1 = roleMapper.selectById(1L);
            System.out.println(JSON.toJSONString(role1));
            role1.setRoleName("New Name");
            SysRole sysRole2 = roleMapper.selectById(1L);
            System.out.println(JSON.toJSONString(sysRole2));
            Assert.assertEquals("New Name",sysRole2.getRoleName());
            Assert.assertEquals(role1,sysRole2);
        }finally {
            sqlSession.close();
        }

        System.out.println("Open new sqlsession");

        sqlSession = getSqlSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole2 = sysRoleMapper.selectById(1L);
            System.out.println(JSON.toJSONString(sysRole2));
            Assert.assertEquals("New Name",sysRole2.getRoleName());
            Assert.assertNotEquals(role1,sysRole2);

            SysRole sysRole3 = sysRoleMapper.selectById(1L);
            System.out.println(JSON.toJSONString(sysRole3));
            Assert.assertNotEquals(sysRole2,sysRole3);
        }finally {
            sqlSession.close();
        }
    }
}
