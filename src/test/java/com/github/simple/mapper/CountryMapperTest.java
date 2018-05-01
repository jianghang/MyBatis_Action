package com.github.simple.mapper;

import com.alibaba.fastjson.JSON;
import com.github.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by jianghang on 2018/4/26.
 */
public class CountryMapperTest extends BaseMapperTest{

    @Test
    public void testSelectOne(){
        SqlSession sqlSession = getSqlSession();
        List<Country> countries = sqlSession.selectList("com.github.simple.mapper.CountryMapper.selectAll");
        System.out.println(JSON.toJSONString(countries));
        Country country = sqlSession.selectOne("com.github.simple.mapper.CountryMapper.selectByPrimaryKey",1);
        System.out.println(JSON.toJSONString(country));
        sqlSession.close();
    }

}
