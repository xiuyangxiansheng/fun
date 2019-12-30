package com.springboot.fun;

import com.springboot.fun.entity.TestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunApplicationTests {



    @Resource
    private com.springboot.fun.dao.TestDao testDao;

    @Test
    public void contextLoads() {

        TestEntity testEntity = new TestEntity(1, "lxb");
        int num = testDao.saveUser(testEntity);
        System.out.println(num);
    }

}
