package com.levent.spring.study_0306_jpa.dao;

import com.levent.spring.study_0306_jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserDao extends JpaRepository<JpaUser,Long> {

    JpaUser findByUsername(String username) ;

    List<JpaUser> findAllBy();

}
