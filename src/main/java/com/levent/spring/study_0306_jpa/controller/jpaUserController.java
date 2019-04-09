package com.levent.spring.study_0306_jpa.controller;


import com.levent.spring.study_0306_jpa.config.RedisDao;
import com.levent.spring.study_0306_jpa.entity.JpaUser;
import com.levent.spring.study_0306_jpa.service.JpaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "用户表测试redis")
public class jpaUserController {

    @Autowired
    JpaUserService jpaUserService ;

    @Autowired
    RedisDao redisDao ;


    @ApiOperation(value = "redis测试接口",notes = "读取数据库列表，写入redis，获取缓存")
    @GetMapping("/reddis")
    public String redis(){
        List<JpaUser> list = jpaUserService.getList();
        StringBuffer sb = new StringBuffer();
        sb.append("*****************打印数据库查询的数据***********<br>");
        for(JpaUser j : list){
            sb.append("user>>>"+ j.getUsername()+"<br>");
            sb.append("pass>>>"+j.getPassword()+"<br>");
            sb.append("id>>>>>"+j.getId()+"<br>");
        }
        sb.append("*************将数据进行缓存***************<br>");
        redisDao.setKey(list);
        sb.append("*************查询redis某一个键值对***************<br>");
        sb.append("缓存444>>"+redisDao.getKey("444"));
        return sb.toString() ;
    }

    @ApiOperation(value="redis测试接口",notes = "添加数据至数据库")
    @GetMapping("add")
    public void add(){
        JpaUser j = new JpaUser();
        j.setCreate_time("333");
        j.setPassword("3333");
        j.setUsername("33333");
        jpaUserService.saveUser(j);
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String usrename){
        JpaUser user = jpaUserService.findUser(usrename);
        StringBuffer sb = new StringBuffer();
        sb.append("****************************<br>");
        sb.append("user>>>"+ user.getUsername()+"<br>");
        sb.append("pass>>>"+user.getPassword()+"<br>");
        sb.append("id>>>>>"+user.getId()+"<br>");
        return sb.toString() ;
    }

    public static void main(String[] args) {




    }

}
