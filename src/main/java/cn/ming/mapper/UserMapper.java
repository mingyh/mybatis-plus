package cn.ming.mapper;

import cn.ming.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by ASUS on 2020/8/25.
 */
//在对应的Mapper上继承基本类BaseMapper
@Repository
public interface UserMapper extends BaseMapper<User> {


    //所有的CRUD都已经完成了，不用像之前配置一堆文件了
}
