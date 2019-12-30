package com.springboot.fun.dao;

import com.springboot.fun.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;

public interface TestDao {


    int saveUser(TestEntity test);

}
