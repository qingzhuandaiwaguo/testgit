package com.levent.spring.study_0306_jpa.service;

import com.levent.spring.study_0306_jpa.dao.JpaUserDao;
import com.levent.spring.study_0306_jpa.entity.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserService {

    @Autowired
    JpaUserDao jpaUserDao;

    public JpaUser findUser(String username){
        return jpaUserDao.findByUsername(username);
    }

    public List<JpaUser> getList(){
        return jpaUserDao.findAll();
    }

    public void saveUser(JpaUser user){
        jpaUserDao.save(user);
    }

}
