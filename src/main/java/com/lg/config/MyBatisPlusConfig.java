package com.lg.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// 设置mapper接口的扫描包
@MapperScan("com.lg.mapper")
public class MyBatisPlusConfig {

    /*相当于<bean id="" class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor" ></bean>*/
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
