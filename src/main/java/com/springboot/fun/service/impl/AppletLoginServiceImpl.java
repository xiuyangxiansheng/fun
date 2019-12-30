package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.AppletLoginDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.AppletLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class AppletLoginServiceImpl implements AppletLoginService {


    @Autowired
    private AppletLoginDao appletLoginDao;

    //根据openID查找在不在数据库
    @Override
    public WxUser selectByOpenId(String openId) {


        return appletLoginDao.selectByOpenId(openId);
    }
    //更新用户信息
    @Override
    public void updateMemberById(WxUser member) {


        appletLoginDao.updateMemberById(member);
    }
    // 添加到数据库
    @Override
    public Boolean insertMember(WxUser insertMember) {

        return appletLoginDao.insertMember(insertMember);
    }
    /*存图片oss*/
    @Override
    public void insertImg(String url) {

        appletLoginDao.insertImg(url);
    }
    //添加到好友列表
    @Override
    public void insertShareMember(String uUid, String friendUserId, String shareId) {
        YlqFriend ylqFriend=new YlqFriend();
        ylqFriend.setuUid(uUid);
        ylqFriend.setShareId(shareId);
        ylqFriend.setFriendUserId(friendUserId);
        appletLoginDao.insertShareMember(ylqFriend);
    }

    /*根据用户id查看用户信息*/
    @Override
    public WxUser selectByUserId(String id) {
        return appletLoginDao.selectByUserId(id);
    }
    // 更新到数据库
    @Override
    public Boolean updateMember(WxUser insertWxUser) {
        return appletLoginDao.updateMember(insertWxUser);
    }
    //获取用户信息
    @Override
    public WxUser finUsedUserInf(String id) {
        return appletLoginDao.finUsedUserInf(id);
    }
    //查看用户在 xcx_3rd_session有没有值，有的话加，没有的话更新
    @Override
    public void updateXcx3rdSessionById(Xcx3rdSession xcx3rdSession) {
        appletLoginDao.updateXcx3rdSessionById(xcx3rdSession);
    }
    @Override
    public Xcx3rdSession selectXcx3rdSession(String openId) {

        return appletLoginDao.selectXcx3rdSession(openId);
    }
    @Override
    public void insertXcx3rdSession(Xcx3rdSession rdSession) {
            appletLoginDao.insertXcx3rdSession(rdSession);
    }
    //奖励运动币
    @Override
    public void addCoinByUserId(Map<String,Object> addCoinMap) {
            appletLoginDao.addCoinByUserId(addCoinMap);
    }
    //查找整个学校的信息
    @Override
    public List<SchoolEntity> findAllSchool() {
        return appletLoginDao.findAllSchool();
    }
    //根据学校的名字搜索学校
    //用户切换校区
    @Override
    public List findSchoolByName(String name) {
        return appletLoginDao.findSchoolByName(name);
    }
    //查找这个学校的信息
    @Override
    public SchoolEntity selectSchoolById(String schoolId) {
        return appletLoginDao.selectSchoolById(schoolId);
    }
    //更新用户信息
    @Override
    public Integer updateWxUserById(Map<String, String> userInf) {
        return appletLoginDao.updateWxUserById(userInf);
    }
    //创建用户钱包
    @Override
    public void insertWallet(YlqWallet ylqWallet) {
        appletLoginDao.insertWallet(ylqWallet);
    }

    //查找用户的动力币明细
     @Override
     public PageInfo<YlqWalletDetail> findUserWalletDetail(int page, int pageSize, String userId) {
         PageHelper.startPage(page, pageSize);
         List<YlqWalletDetail> lists = new ArrayList<>();
         lists =appletLoginDao.findUserWalletDetail(userId);
         PageInfo<YlqWalletDetail> pi = new PageInfo<>(lists);
         return pi;

    }
    /*查看前100名本周的排行榜*/
    @Override
    public PageInfo<YlqUserStep> selectWalletRankingList(int page, int pageSize, Map<Object, Object> map) {
        PageHelper.startPage(page, pageSize);
        List<YlqUserStep> lists = new ArrayList<>();
        lists =appletLoginDao.selectWalletRankingList(map);
        PageInfo<YlqUserStep> pi = new PageInfo<>(lists);
        return pi;
    }
    //查找用户id有没有重复，有，就再次生成
    @Override
    public String findUserId(String userId) {
        return appletLoginDao.findUserId(userId);
    }
    //用户总量加一
    @Override
    public void addNoticeUserNum() {
        appletLoginDao.addNoticeUserNum();
    }
    //给用户的浏览量加一
    @Override
    public void addNoticeBrowseNum() {
        appletLoginDao.addNoticeBrowseNum();
    }
    //获取公告信息
    @Override
    public List<FunNotice>  finNoticeInf() {
        return appletLoginDao.finNoticeInf();
    }
    //改变用户的领取新用户红包完成状态
    @Override
    public void updateTagRedPacket(String userId) {
        appletLoginDao.updateTagRedPacket(userId);
    }
    //改变用户的收藏小程序红包完成状态
    @Override
    public void updateTagCollection(String userId) {
        appletLoginDao.updateTagCollection(userId);
    }

    @Override
    public void updateTagUsed(String usedUserId) {
        appletLoginDao.updateTagUsed(usedUserId);
    }
    //生成用户任务表单
    @Override
    public void insertUserTag(YlqUserTag ylqUserTag) {
        appletLoginDao.insertUserTag(ylqUserTag);
    }
    //改变用户的激励小视频状态
    @Override
    public void updateVideo(String userId) {
        appletLoginDao.updateVideo(userId);
    }
    //查看微信用户列表
    @Override
    public PageInfo<WxUser> findWXUserList(int page, int pageSize, Map<String, Object> usedMap) {
        PageHelper.startPage(page, pageSize);
        List<WxUser> lists = new ArrayList<>();
        lists =appletLoginDao.findWXUserList(usedMap);
        PageInfo<WxUser> pi = new PageInfo<>(lists);
        return pi;
    }
    //模糊搜索用户（昵称，主键id动力币余额，手机号，唯一id）
    @Override
    public PageInfo<WxUser> searchMapWXUserList(int page, int pageSize, Map<String, Object> searchMap) {
        PageHelper.startPage(page, pageSize);
        List<WxUser> lists = new ArrayList<>();
        lists =appletLoginDao.searchMapWXUserList(searchMap);
        PageInfo<WxUser> pi = new PageInfo<>(lists);
        return pi;
    }
    //修改动力币
    @Override
    public Integer updateUserWallet(YlqWallet ylqWallets) {
        return appletLoginDao.updateUserWallet(ylqWallets);
    }
    //拉黑用户
    @Override
    public Integer updateUserStatus(String userId) {
        return appletLoginDao.updateUserStatus(userId);
    }
    //解封用户
    @Override
    public Integer updateUserStatusOpen(String id) {
        return appletLoginDao.updateUserStatusOpen(id);
    }
    //根据用户唯一id或者昵称模糊查找用户(商家的添加员工需返回)
    @Override
    public  List<WxUser> searchMapWXUserByName(String search) {
        return appletLoginDao.searchMapWXUserByName(search);
    }
    //首页轮播图展示
    @Override
    public List<FunHomeUrl> findHomeUrl(String schoolId) {
        return appletLoginDao.findHomeUrl(schoolId);
    }
//极光注册状态
    @Override
    public void updateUserJiGuangState(String id) {
        appletLoginDao.updateUserJiGuangState(id);
    }
    //查询有没有任务表
    @Override
    public YlqUserTag selectylqUserTag(String id) {
        return appletLoginDao.selectylqUserTag(id);
    }
    //男生随机拿去5条数据
    @Override
    public String selectTaskList(Map<String, Object> list) {
        return appletLoginDao.selectTaskList(list);
    }
    //其他人随机拿去5条数据
    @Override
    public String selectTaskListElse(Map<String, Object> feeling) {
        return appletLoginDao.selectTaskListElse(feeling);
    }
    //把这个用户的动态都改为下架

    @Override
    public void updateUsedOut(String id) {
        appletLoginDao.updateUsedOut(id);
    }
    //修改动力币
    @Override
    public Integer updateWallet(WxUser wxUsers) {
        return appletLoginDao.updateWallet(wxUsers);
    }
    //改变用户的校园优选优惠劵状态
    @Override
    public void updateSchoolCoupon(String userId) {
        appletLoginDao.updateSchoolCoupon(userId);
    }


}
