package com.springboot.fun.dao;

import com.springboot.fun.util.oss.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置信息
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月4日 下午6:46:16
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

    /**
     * 根据key，查询value
     */
    String queryByKey(String paramKey);

    /**
     * 根据key，更新value
     */
   /* int updateValueByKey(@Param("key") String key, @Param("value") String value);*/
    void updateValueByKey(@Param("key") String key, @Param("value") String value);
/*  String nex_seq(String seq_name);*/
}
