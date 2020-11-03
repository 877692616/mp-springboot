package com.lg.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 无参构造
@NoArgsConstructor
// 有参构造
@AllArgsConstructor
// 加载get() set() 方法
@Data
// 跟数据库中的哪一个表关联
@TableName("tb_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    // 查询的时候，不想让别人看到密码
    @TableField(select = false)
    private String password;
    private String name;
    private Integer age;
    /*
    // 当实体类名和表名不一致时
    @TableField(value = "email")
    */
    private String email;
    // 查询条件不是数据库的字段时
    @TableField(exist = false)
    private String address;

}
