package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.DeployDao;
import com.springboot.fun.dao.MeDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.DeployService;
import com.springboot.fun.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class DeployServiceImpl implements DeployService {

    /*
     * 后台端配置
     *
     * */

    @Autowired
    private DeployDao deployDao;


    //查询签到列表
    @Override
    public List<YlqSignSetting> selectSignSetting() {
        return deployDao.selectSignSetting();
    }
    //编辑前返回签到信息
    @Override
    public List<YlqSignSetting> selectSignSettingById(String id) {
        return deployDao.selectSignSettingById(id);
    }
    //编辑签到配置信息
    @Override
    public Integer updateSignSettingById(YlqSignSetting ylqSignSettings) {
        return deployDao.updateSignSettingById(ylqSignSettings);
    }
    //增加签到配置信息
    @Override
    public Integer insertSignSetting(YlqSignSetting ylqSignSettings) {
        return deployDao.insertSignSetting(ylqSignSettings);
    }
    //获取校区列表
    @Override
    public PageInfo<YlqSchool> searchSchoolList(int page, int pageSize, Map<String, Object> schoolMap) {
        PageHelper.startPage(page, pageSize);
        List<YlqSchool> lists = new ArrayList<>();
        lists = deployDao.searchSchoolList(schoolMap);
        PageInfo<YlqSchool> pi = new PageInfo<>(lists);
        return pi;
    }
    //编辑前返回学校信息
    @Override
    public List<YlqSchool> searchSchoolById(String id) {
        return deployDao.searchSchoolById(id);
    }
    //编辑学校信息
    @Override
    public Integer updateSchoolById(YlqSchool ylqSchools) {
        return deployDao.updateSchoolById(ylqSchools);
    }
    //添加学校
    @Override
    public Integer addSchool(YlqSchool ylqSchools) {
        return deployDao.addSchool(ylqSchools);
    }
    //查询公告列表
    @Override
    public List<FunNotice> selectNotice() {
        return deployDao.selectNotice();
    }
    //编辑前返回公告信息
    @Override
    public List<FunNotice> selectNoticeById(String id) {
        return deployDao.selectNoticeById(id);
    }
    //编辑公告配置信息
    @Override
    public Integer updateNoticeById(FunNotice funNotice1) {
        return deployDao.updateNoticeById(funNotice1);
    }
    //增加公告配置信息
    @Override
    public Integer insertNotice(FunNotice funNotice1) {
        return deployDao.insertNotice(funNotice1);
    }
    //关闭公告
    @Override
    public Integer closeNotice(String id) {
        return deployDao.closeNotice(id);
    }
    //打开公告
    @Override
    public Integer openNotice(String id) {
        return deployDao.openNotice(id);
    }
    //查询轮播图列表
    @Override
    public List<FunHomeUrl> selectHomeUrl() {
        return deployDao.selectHomeUrl();
    }
    //编辑前返回轮播图信息
    @Override
    public List<FunHomeUrl> selectHomeUrlById(String id) {
        return deployDao.selectHomeUrlById(id);
    }
    //编辑轮播图配置信息
    @Override
    public Integer updateHomeUrlById(FunHomeUrl funHomeUrl1) {
        return deployDao.updateHomeUrlById(funHomeUrl1);
    }
    //增加轮播图配置信息
    @Override
    public Integer insertHomeUrl(FunHomeUrl funHomeUrl1) {
        return deployDao.insertHomeUrl(funHomeUrl1);
    }
    //关闭轮播图
    @Override
    public Integer closeHomeUrl(String id) {
        return deployDao.closeHomeUrl(id);
    }
    //打开轮播图
    @Override
    public Integer openHomeUrl(String id) {
        return deployDao.openHomeUrl(id);
    }
    //查询任务列表
    @Override
    public List<YlqTask> selectTask() {
        return deployDao.selectTask();
    }
    //编辑前返回任务信息
    @Override
    public List<YlqTask> selectTaskById(String id) {
        return deployDao.selectTaskById(id);
    }
    //编辑任务配置信息
    @Override
    public Integer updateTaskById(YlqTask ylqTask1) {
        return deployDao.updateTaskById(ylqTask1);
    }
    //增加任务配置信息
    @Override
    public Integer insertTask(YlqTask ylqTask1) {
        return deployDao.insertTask(ylqTask1);
    }
    //关闭任务
    @Override
    public Integer closeTask(String id) {
        return deployDao.closeTask(id);
    }
    //打开任务
    @Override
    public Integer openTask(String id) {
        return deployDao.openTask(id);
    }


}
