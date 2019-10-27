package com.github.simple.mapper;

import com.alibaba.fastjson.JSON;
import com.github.interceptors.PageRowBounds;
import com.github.model.SysRole;
import com.github.model.SysUser;
import com.github.model.SysUserWithBLOBs;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by jianghang on 2018/4/29.
 */
public class SysUserMapperTest extends BaseMapperTest {

    private static Logger logger = LoggerFactory.getLogger(SysUserMapperTest.class);

    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = sysUserMapper.selectByPrimaryKey(1);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Map<String, Object>> mapList = sysUserMapper.selectUserById(1);
            logger.info("mapList:{}", mapList);

            Assert.assertNotNull(mapList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> userList = sysUserMapper.selectAll();
            logger.info("userList: {}", JSON.toJSONString(userList));
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> roleList = sysUserMapper.selectRolesByUserId(1);
            logger.info("roleList: {}", JSON.toJSONString(roleList));
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserWithBLOBs sysUser = new SysUserWithBLOBs();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@qq.com");
            sysUser.setUserInfo("test info");
//            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            int result = sysUserMapper.insert(sysUser);
            logger.info("result: {}", result);

            Assert.assertEquals(1, result);
//            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.commit();
//            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserWithBLOBs sysUser = new SysUserWithBLOBs();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@qq.com");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            int result = sysUserMapper.insert2(sysUser);
            logger.info("result: {}", result);

            Assert.assertEquals(1, result);
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserWithBLOBs sysUser = new SysUserWithBLOBs();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@qq.com");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            int result = sysUserMapper.insert3(sysUser);
            logger.info("result: {}", result);

            Assert.assertEquals(1, result);
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser query = new SysUser();
            query.setUserName("ad");

            List<SysUser> userList = sysUserMapper.selectByUser(query);
            logger.info("userList: {}", JSON.toJSONString(userList));
            Assert.assertTrue(userList.size() > 0);

            query = new SysUser();
//            query.setUserName("ad");
            query.setUserEmail("admin@qq.com");
            userList = sysUserMapper.selectByUser(query);
            logger.info("userList: {}", JSON.toJSONString(userList));

            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Integer> idList = Arrays.asList(1, 1001);

            List<SysUserWithBLOBs> userList = sysUserMapper.selectByIdList(idList);
            logger.info("userList: {}", JSON.toJSONString(userList));
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUserWithBLOBs> sysUserWithBLOBsList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                SysUserWithBLOBs sysUserWithBLOBs = new SysUserWithBLOBs();
                sysUserWithBLOBs.setUserName("test" + i + i);
                sysUserWithBLOBs.setUserPassword("123456");
                sysUserWithBLOBs.setUserEmail("test@qq.com");
                sysUserWithBLOBsList.add(sysUserWithBLOBs);
            }

            int result = sysUserMapper.insertList(sysUserWithBLOBsList);

            Assert.assertEquals(2, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("user_email", "admin1@qq.com");
            map.put("user_password", "12345678");

            sysUserMapper.updateByMap(map);

            SysUserWithBLOBs sysUserWithBLOBs = sysUserMapper.selectByPrimaryKey(1);
            logger.info("sysUser: {}", JSON.toJSONString(sysUserWithBLOBs));

            Assert.assertEquals("admin1@qq.com", sysUserWithBLOBs.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUsers = sysUserMapper.selectUserAndRoleById(1001);
            logger.info("call user.getSysRole()");

            sysUsers.get(0).getSysRole();

            Assert.assertTrue(sysUsers.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserByRowBounds() {
        SqlSession sqlSession = getSqlSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUserList = sysUserMapper.selectAll();
        logger.info("count: {}, {}", sysUserList.size(), JSON.toJSONString(sysUserList));

        PageRowBounds pageRowBounds = new PageRowBounds(0, 1);
        sysUserList = sysUserMapper.selectUserAll(pageRowBounds);
        logger.info("count: {},result: {}",pageRowBounds.getTotal(),JSON.toJSONString(sysUserList));

        String sql = "select * from sys_user";
        List<Map<String,Object>> mapList = sysUserMapper.selectSomeThing(sql);
        logger.info(JSON.toJSONString(mapList));
        sqlSession.close();
    }
}
