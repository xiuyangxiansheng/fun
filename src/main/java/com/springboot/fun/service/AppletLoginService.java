package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AppletLoginService {

    //根据openID查找在不在数据库
    WxUser selectByOpenId(String openId);
    //更新用户信息
    void updateMemberById(WxUser member);
    // 添加到数据库
    Boolean insertMember(WxUser insertMember);
    /*存图片oss*/
    void insertImg(String url);
    //添加到好友列表
    void insertShareMember(String uUid, String friendUserId, String shareId);

    /*根据用户id查看用户信息*/
    WxUser selectByUserId(String id);
    // 更新到数据库
    Boolean updateMember(WxUser insertWxUser);
    //获取用户信息
    WxUser finUsedUserInf(String id);
    //查看用户在 xcx_3rd_session有没有值，有的话加，没有的话更新
    void updateXcx3rdSessionById(Xcx3rdSession xcx3rdSession);
    Xcx3rdSession selectXcx3rdSession(String openId);
    void insertXcx3rdSession(Xcx3rdSession rdSession);
    //奖励运动比
    void addCoinByUserId(Map<String,Object> addCoinMap);
    //查找整个学校的信息
    List<SchoolEntity> findAllSchool();
    //根据学校的名字搜索学校
    List findSchoolByName(String name);
    //用户切换校区
    //查找这个学校的信息
    SchoolEntity selectSchoolById(String schoolId);
    //更新用户信息
    Integer updateWxUserById(Map<String,String> userInf);
    //创建用户钱包
    void insertWallet(YlqWallet ylqWallet);
    //查找用户的动力币明细
    PageInfo<YlqWalletDetail> findUserWalletDetail(int page, int pageSize, String userId);
    /*查看前100名本周的排行榜*/
    PageInfo<YlqUserStep> selectWalletRankingList(int page, int pageSize, Map<Object,Object> map);
    //查找用户id有没有重复，有，就再次生成
    String findUserId(String userId);

    //用户总量加一
    void addNoticeUserNum();
    //给用户的浏览量加一
    void addNoticeBrowseNum();
    //获取公告信息
    List<FunNotice>  finNoticeInf();
    //改变用户的领取新用户红包完成状态
    void updateTagRedPacket(String userId);
    //改变用户的收藏小程序红包完成状态
    void updateTagCollection(String userId);
    //改变用户的领取发布二手完成状态
    void updateTagUsed(String usedUserId);
    //生成用户任务表单
    void insertUserTag(YlqUserTag ylqUserTag);
    //改变用户的激励小视频状态
    void updateVideo(String userId);
    //查看微信用户列表
    PageInfo<WxUser> findWXUserList(int page, int pageSize, Map<String,Object> usedMap);
    //模糊搜索用户（昵称，主键id动力币余额，手机号，唯一id）
    PageInfo<WxUser> searchMapWXUserList(int page, int pageSize, Map<String,Object> searchMap);
    //修改动力币
    Integer updateUserWallet(YlqWallet ylqWallets);
    //拉黑用户
    Integer updateUserStatus(String userId);
    //解封用户
    Integer updateUserStatusOpen(String id);
    //根据用户唯一id或者昵称模糊查找用户(商家的添加员工需返回)
    List<WxUser> searchMapWXUserByName(String search);
    //首页轮播图展示
    List<FunHomeUrl> findHomeUrl(String schoolId);
    //修改用户极光状态
    void updateUserJiGuangState(String id);
    //查询有没有任务表
    YlqUserTag selectylqUserTag(String id);
    //男生随机拿去5条数据
    String selectTaskList(Map<String,Object> list);
    //其他人随机拿去5条数据
    String selectTaskListElse(Map<String,Object> feeling);
    //把这个用户的动态都改为下架
    void updateUsedOut(String id);
    //修改动力币
    Integer updateWallet(WxUser wxUsers);
    //改变用户的校园优选优惠劵状态
    void updateSchoolCoupon(String userId);

}
