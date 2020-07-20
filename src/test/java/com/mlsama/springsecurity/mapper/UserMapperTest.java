package com.mlsama.springsecurity.mapper;

import com.mlsama.springsecurity.SpringSecurityApplication;
import com.mlsama.springsecurity.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = SpringSecurityApplication.class)
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void update(){
        // 条件
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username","魔人布欧");
        SysUser user = new SysUser();
        user.setState("0");
        // 会set id=?,user没有id不行
        userMapper.updateByExampleSelective(user,example);
    }

    @Test
    public void select(){
        Example example = new Example(SysUser.class);
        // SELECT ID,username,password,state FROM T_DAP_USER WHERE username = '魔人布欧'
        example.and().andEqualTo("username","魔人布欧");
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        System.out.println(sysUsers);
    }

}