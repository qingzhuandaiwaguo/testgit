package com.levent.spring.study_0306_jpa.config;


import com.levent.spring.study_0306_jpa.entity.JpaUser;
import com.levent.spring.study_0306_jpa.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisDao {


    /***
     * add by guojing
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;


    /****
     * 从数据库中查询数据列表，将每一条数据缓存redis
     * @param userList
     */
    public void setKey(List<JpaUser> userList){
        ValueOperations<String,String> opt = stringRedisTemplate.opsForValue();
        for(JpaUser u : userList){
            opt.set(u.getUsername(),u.getPassword(),1000, TimeUnit.MINUTES); //1分钟过期
        }
    }

    public String getKey(String k ){
        ValueOperations<String,String> opt = stringRedisTemplate.opsForValue();
        return opt.get(k);
    }
}
