package cn.ming.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by ASUS on 2020/8/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //对应数据库中的主键：(uuid，自增id，雪花算法，redis，zookeeper)

    @TableId(type = IdType.AUTO)
    //配置主键自增方式，然后修改数据库表id为自增
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version    //乐观锁version注解
    private Integer version;

    @TableLogic     //逻辑删除
    private Integer deleted;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
