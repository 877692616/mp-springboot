package com.lg.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.mapper.UserMapper;
import com.lg.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 自动创建 spring容器
@RunWith(SpringRunner.class)
// 读取配置文件
@SpringBootTest
public class MpTest {

    // 自动注入
    @Autowired
    private UserMapper userMapper;

    // 查询全部
    @Test
    public void SelectAll(){
        System.out.println("我是拉下来的！修改了！");
        List<User> list = userMapper.selectList(null);
        list.forEach((item) -> System.out.println(item));
    }

    // 插入一条数据
    @Test
    public void insertOne(){
        User user = new User();
        user.setUserName("guer");
        user.setPassword("123456");
        user.setName("顾二");
        user.setAge(22);
        user.setEmail("654321@qq.com");
        int insert = this.userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());
    }

    // 根据id 修改一条数据
    @Test
    public void update(){
        User user = new User();
        user.setId(5L);
        user.setPassword("654321");
        int i = this.userMapper.updateById(user);
        System.out.println(i);
        System.out.println(user.getPassword());
    }

    // 使用QueryWrapper包装
    @Test
    public void update2(){
        User user = new User();
        // 更新的字段
        user.setEmail("guer@qq.com");
        // 更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",6L);
        int update = this.userMapper.update(user, wrapper);
    }

    @Test
    public void update3(){
        // 更新的条件及字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",6L).set("user_name","顾");

        // 执行更新操作
        int update = this.userMapper.update(null, wrapper);
    }

    // 根据id删除数据
    @Test
    public void delete(){
        int i = this.userMapper.deleteById(6L);
        System.out.println(i);
    }

    // 根据字段名删除
    @Test
    public void delete2(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","guer");
        map.put("password","123456");
        //将map中的元素设置为删除的条件，多个之间为and关系
        int i = this.userMapper.deleteByMap(map);
        System.out.println(i);
    }

    // 将实体对象作为条件，删除对象
    @Test
    public void delete3(){
        User user = new User();
        user.setName("顾二");
        //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        this.userMapper.delete(wrapper);
    }

    // 根据id批量删除
    @Test
    public void delete4(){
        int i = this.userMapper.deleteBatchIds(Arrays.asList(9L, 10L));
        System.out.println(i);
    }

    // 根据id进行查询
    @Test
    public void select(){
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }

    // 根据id批量查询
    @Test
    public void select2(){
        List<User> list = this.userMapper.selectBatchIds(Arrays.asList(1L, 3L, 5L));
        list.forEach((item)-> System.out.println(item));
    }

    // 根据条件查询信息
    @Test
    public void select3(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("user_name", "lisi");
        // 根据条件查询一条数据，如果结果超过一条会报错
        User user = this.userMapper.selectOne(objectQueryWrapper);
        System.out.println(user);
    }

    // 根据条件 查询总计数量
    @Test
    public void select4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // wrapper.eq("password","123456");
        wrapper.ge("age",20);
        Integer i = this.userMapper.selectCount(wrapper);
        List<User> list = this.userMapper.selectList(wrapper);
        System.out.println(i);
        list.forEach((item)-> System.out.println(item));
    }

    // 使用mybatis-plus的分页
    @Test
    public void select5(){
        // 条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.le("age",24);

        // mybatis-plus提供的一个类
        Page<User> page = new Page<>(1,2);

        // 根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("数据的总条数："+iPage.getTotal());
        // 查询到的结果集
        List<User> records = iPage.getRecords();
        records.forEach((item)-> System.out.println(item));
    }

    // 自定义方法，查询
    @Test
    public void select6(){
        User user = this.userMapper.queryById(1L);
        System.out.println(user);
    }

    // 条件构造器
    @Test
    public void wrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","顾二");
        map.put("age",22);
        map.put("password",null);

        // 条件构造器
        /*
           params : key 为数据库字段名, value 为字段值
           null2IsNull : 为true 则在map 的value 为null 时调用 isNull 方法,为false 时则忽略value 为null 的
        */
        // wrapper.allEq(map);
        wrapper.allEq(map,false);

        List<User> list = this.userMapper.selectList(wrapper);
        list.forEach((item)-> System.out.println(item));
    }

    // 模糊查询
    @Test
    public void like(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name","李");
        /*
        * User(id=2, userName=lisi, password=null, name=李四, age=20, email=test2@bigdata.com, address=null)
        * User(id=3, userName=wangwu, password=null, name=王李, age=28, email=test3@bigdata.com, address=null)
        * */
        // 排序  降序排列
        wrapper.orderByDesc("age");
        //wrapper.likeLeft("name","六");
        /*User(id=4, userName=zhaoliu, password=null, name=赵六, age=21, email=test4@bigdata.com, address=null)*/
        List<User> list = this.userMapper.selectList(wrapper);
        list.forEach((item)-> System.out.println(item));
}

    // 逻辑查询
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","顾二").or().eq("age",24).or().in("age",18,28);
        // 指定想要拿到的字段
        wrapper.select("id","name","age");
        List<User> list = this.userMapper.selectList(wrapper);
        list.forEach((item)-> System.out.println(item));
    }

}
