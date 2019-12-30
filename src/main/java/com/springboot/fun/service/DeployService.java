package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface DeployService {


    /*
     * 后台端
     *
     * */
    //查询签到列表
    List<YlqSignSetting> selectSignSetting();
    //编辑前返回签到信息
    List<YlqSignSetting> selectSignSettingById(String id);
    //编辑签到配置信息
    Integer updateSignSettingById(YlqSignSetting ylqSignSettings);
    //增加签到配置信息
    Integer insertSignSetting(YlqSignSetting ylqSignSettings);

    //获取校区列表
    PageInfo<YlqSchool> searchSchoolList(int page, int pageSize, Map<String,Object> schoolMap);
    //编辑前返回学校信息
    List<YlqSchool> searchSchoolById(String id);
    //编辑学校信息
    Integer updateSchoolById(YlqSchool ylqSchools);
    //添加学校
    Integer addSchool(YlqSchool ylqSchools);
    //查询公告列表
    List<FunNotice> selectNotice();
    //编辑前返回公告信息
    List<FunNotice> selectNoticeById(String id);
    //编辑公告配置信息
    Integer updateNoticeById(FunNotice funNotice1);
    //增加公告配置信息
    Integer insertNotice(FunNotice funNotice1);
    //关闭公告
    Integer closeNotice(String id);
    //打开公告
    Integer openNotice(String id);
    //查询轮播图列表
    List<FunHomeUrl> selectHomeUrl();
    //编辑前返回轮播图信息
    List<FunHomeUrl> selectHomeUrlById(String id);
    //编辑轮播图配置信息
    Integer updateHomeUrlById(FunHomeUrl funHomeUrl1);
    //增加轮播图配置信息
    Integer insertHomeUrl(FunHomeUrl funHomeUrl1);
    //关闭轮播图
    Integer closeHomeUrl(String id);
    //打开轮播图
    Integer openHomeUrl(String id);
    //查询任务列表
    List<YlqTask> selectTask();
    //编辑前返回任务信息
    List<YlqTask> selectTaskById(String id);
    //编辑任务配置信息
    Integer updateTaskById(YlqTask ylqTask1);
    //增加任务配置信息
    Integer insertTask(YlqTask ylqTask1);
    //关闭任务
    Integer closeTask(String id);
    //打开任务
    Integer openTask(String id);
}
