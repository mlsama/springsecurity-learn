package com.mlsama.springsecurity.mapper;

import com.mlsama.springsecurity.entity.GrantKey;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * DESC:
 * AUTHOR:mlsama
 * 2020/6/28 11:55
 */
public interface GrantKeyMapper extends Mapper<GrantKey> {

    @Select("select grant_key" +
            "  from T_DAP_USER_ROLE     t1," +
            "       T_DAP_ROLE          t2," +
            "       T_DAP_ROLE_RESOURCE t3," +
            "       T_DAP_RESOURCE      t4" +
            " where t1.role_id = t2.id" +
            "   and t2.id = t3.role_id" +
            "   and t3.resource_id = t4.id" +
            "   and t4.grant_key is not null" +
            "   and t1.user_id = #{userId}")
    List<GrantKey> selectUserGrantKeys(Long userId);
}
