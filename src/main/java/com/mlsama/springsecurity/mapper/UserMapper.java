package com.mlsama.springsecurity.mapper;

import com.mlsama.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * DESC:
 * AUTHOR:mlsama
 * 2020/6/28 11:05
 */
public interface UserMapper extends Mapper<SysUser> {

    /**
     * @Results: 数据库字段与实体字段不一致时进行映射
     * @Result: 映射一个字段
     */
    @Select("select id,username,password,state from T_DAP_USER where username=#{username}")
    @Results({
            // id = true: 指定该字段为主键
            @Result(id = true,column = "ID",property = "id"),
            /**
             *  一对多时,
             *  column: 指定查询条件
             *  property: 查询结果存到实体哪个字段
             *  javaType: 实体类型
             *  many: 一对多(one: 一对一)
             *       @Many: 指定查询语句或者接口全路径
             */
            @Result(column = "ID",property = "grantKeys",javaType=List.class,
            many = @Many(select = "com.mlsama.springsecurity.mapper.GrantKeyMapper.selectUserGrantKeys"))
    })
    SysUser selectUserByUsername(String username);
}
