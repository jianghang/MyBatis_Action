package com.github.simple.mapper;

import com.alibaba.fastjson.JSON;
import com.github.model.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jianghang on 2018/4/29.
 */
public class SysRoleMapperTest extends BaseMapperTest{

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole = sysRoleMapper.selectById(1L);

            System.out.println(sysRole.getEnabled().getValue());

            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByPrimaryKey(){
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(1L);

            Assert.assertNotNull(sysRole);
        }finally {
            sqlSession.close();
        }
    }

}
