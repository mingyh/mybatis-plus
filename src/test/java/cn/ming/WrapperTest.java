package cn.ming;

import cn.ming.mapper.UserMapper;
import cn.ming.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2020/8/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空，年龄大于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").isNotNull("email").ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void testName(){
        //查询名字单子
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","单子");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testAge(){
        //查询15-20岁的人数量
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",15,20);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    public void testMohu(){
        //模糊查询
        // 名字中没有 单 字的，email中以  t%  匹配的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","单").likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id 在子查询中查出
        wrapper.inSql("id","select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }


    //根据id排序
    @Test
    public void testOrderById(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
