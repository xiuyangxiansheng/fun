package com.springboot.fun.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.fun.dao.UsedDao;
import com.springboot.fun.entity.*;
import com.springboot.fun.service.UsedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UsedServiceImpl implements UsedService {


    @Autowired
    private UsedDao usedDao;

    /*
     * 小程序端
     *
     * */
    //获取二手列表
    @Override
    public PageInfo<Used> finUsedList(int page, int pageSize, Map<String, Object> usedMap) {
        PageHelper.startPage(page, pageSize);
        List<Used> lists = new ArrayList<>();
        lists = usedDao.finUsedList(usedMap);
        PageInfo<Used> pi = new PageInfo<>(lists);
        return pi;
    }

    //获取二手类型
    @Override
    public List<UsedLabel> selectUsedLabelsType() {

        return usedDao.selectUsedLabelsType();
    }
    //获取二手标签
    @Override
    public List<UsedLabel> selectUsedLabelsName() {

        return usedDao.selectUsedLabelsName();
    }
    //获取学校id
    @Override
    public String findSchoolIdByUserId(String userId) {

        return usedDao.findSchoolIdByUserId(userId);
    }
    //用户点赞
    @Override
    public Integer insertUsedLike(String id, String likeUsedId, String likeUserId, String likeUserDoId) {

        UsedLike usedLike=new UsedLike();
        usedLike.setId(id);
        usedLike.setLikeUsedId(likeUsedId);
        usedLike.setLikeUserId(likeUserId);
        usedLike.setLikeUserDoId(likeUserDoId);
        return usedDao.insertUsedLike(usedLike);
    }
    //给发布二手用户赞加一
    @Override
    public void updateUsedLikeAdd(String likeUsedId) {


        usedDao.updateUsedLikeAdd(likeUsedId);
    }
    //二手发布详情
    @Override
    public List<Used> findUsedById(Map<String, Object> map)
    {
        return usedDao.findUsedById(map);
    }
    //点赞列表
    @Override
    public List<UsedLike> findUsedLikeById(String id) {

        return usedDao.findUsedLikeById(id);
    }
    //评论列表
    @Override
    public List<UsedCommentReply> findUsedCommentById(String id) {
        return usedDao.findUsedCommentById(id);
    }
    //给浏览量加一
    @Override
    public void updateUsedComplainAdd(String id) {
        usedDao.updateUsedComplainAdd(id);
    }
    //根据主键id查询用户id
    @Override
    public String selectLikeUserId(String likeUsedId) {

        return usedDao.selectLikeUserId(likeUsedId);
    }

    //获取用户发布所有评论
    @Override
    public PageInfo<UsedComment> findUsedAllComment(int page, int pageSize, String id) {

        PageHelper.startPage(page, pageSize);
        List<UsedComment> lists = new ArrayList<>();
        lists = usedDao.findUsedAllComment(id);
        PageInfo<UsedComment> pi = new PageInfo<>(lists);
        return pi;
    }
    //给用户评论
    @Override
    public Integer insertUsedComment(UsedComment usedComments) {
        return usedDao.insertUsedComment(usedComments);
    }
    //校区
    @Override
    public String selectSchoolName(String usedUserId) {
        return usedDao.selectSchoolName(usedUserId);
    }
    //发布二手
    @Override
    public Integer insertUsed(String id, String usedUserId, Integer usedType, String usedLable,
                              String usedName, String usedUrl, String usedPrice, String phone,String schoolId,Integer usedPriceState,Integer usedVideoState) {
        Used used=new Used();
        used.setId(id);
        used.setUsedUserId(usedUserId);
        used.setUsedType(usedType);
        used.setUsedLable(usedLable);
        used.setUsedName(usedName);
        used.setUsedUrl(usedUrl);
        used.setUsedPrice(usedPrice);
        used.setPhone(phone);
        used.setSchoolId(schoolId);
        used.setUsedPriceState(usedPriceState);
        used.setUsedVideoState(usedVideoState);
        Integer n=usedDao.insertUsed(used);
        return n;
    }
    //查询用户是否已经点过赞了
    @Override
    public Integer selectUsedLikeById(Map<String, Object> likeStateMap) {
        return usedDao.selectUsedLikeById(likeStateMap);
    }
    //个人中心 我的发布
    @Override
    public PageInfo<Used> findAllSuccessUserList(int page, int pageSize, Map<String, Object> usedMap) {
        PageHelper.startPage(page, pageSize);
        List<Used> lists = new ArrayList<>();
        lists = usedDao.findAllSuccessUserList(usedMap);
        PageInfo<Used> pi = new PageInfo<>(lists);
        return pi;
    }
    //给用户回复
    @Override
    public Integer insertUsedCommentRelpy(UsedCommentReply usedCommentReplys) {
        return usedDao.insertUsedCommentRelpy(usedCommentReplys);
    }
    //删除评论
    @Override
    public Integer deleteComment(String commentId) {
        return usedDao.deleteComment(commentId);
    }
    //删除回复
    @Override
    public Integer deleteCommentReply(String commentReply) {
        return usedDao.deleteCommentReply(commentReply);
    }
    //下架
    @Override
    public Integer outUsed(String id) {
        return usedDao.outUsed(id);
    }
    //投诉
    @Override
    public Integer addComplain(String id) {
        return usedDao.addComplain(id);
    }
    //删除
    @Override
    public Integer deleteUsed(String id) {
        return usedDao.deleteUsed(id);
    }
    //获取全部的二手（不分学校）
    @Override
    public PageInfo<Used> finAllUsedList(int page, int pageSize, Map<String, Object> usedMap) {
        PageHelper.startPage(page, pageSize);
        List<Used> lists = new ArrayList<>();
        lists = usedDao.finAllUsedList(usedMap);
        PageInfo<Used> pi = new PageInfo<>(lists);
        return pi;
    }
    //获取发布和评论的返回内容
    @Override
    public List<FunUsedState> findUsedState(int type) {
        return usedDao.findUsedState(type);

    }
    /*后台*/
    //二手筛选列表
    @Override
    public PageInfo<Used> searchUsedList(int page, int pageSize, Map<String, Object> usedMap) {
        PageHelper.startPage(page, pageSize);
        List<Used> lists = new ArrayList<>();
        lists = usedDao.searchUsedList(usedMap);
        PageInfo<Used> pi = new PageInfo<>(lists);
        return pi;
    }
    //给列表刷新的浏览量+1
    @Override
    public void updateUsedComplainListAdd(Map<String, Object> browseMap) {
        usedDao.updateUsedComplainListAdd(browseMap);
    }
    //二手详情
    @Override
    public Used searchUsedListById(String id) {
        return usedDao.searchUsedListById(id);
    }
    //修改二手类型
    @Override
    public Integer updateUsedType(UsedLabel usedLabels) {
        return usedDao.updateUsedType(usedLabels);
    }
    //添加二手类型
    @Override
    public Integer selectCommodityType() {
        return usedDao.selectCommodityType();
    }
    //查找类型编号的最大值
    @Override
    public Integer insertUsedType(UsedLabel usedLabels) {
        return usedDao.insertUsedType(usedLabels);
    }
    //评论未看的
    @Override
    public Integer selectCommentSum(String userId) {
        return usedDao.selectCommentSum(userId);
    }
    //回复未看的
    @Override
    public Integer selectReplaySum(String userId) {
        return usedDao.selectReplaySum(userId);
    }
    //点击展示未读内容的消息列表
    @Override
    public PageInfo<UsedCommentReplyMessage> selectCommentReplyList(int page, int pageSize, String userId) {
        PageHelper.startPage(page, pageSize);
        List<UsedCommentReplyMessage> lists = new ArrayList<>();
        lists = usedDao.selectCommentReplyList(userId);
        PageInfo<UsedCommentReplyMessage> pi = new PageInfo<>(lists);
        return pi;
    }
    //把评论消息改为已读
    @Override
    public void updateCommentMessage(String userId) {
            usedDao.updateCommentMessage(userId);
    }
    //把回复消息改为已读
    @Override
    public void updateReplayMessage(String userId) {
            usedDao.updateReplayMessage(userId);
    }
    //上架二手类型
    @Override
    public Integer upUsedType(String id) {
        return usedDao.upUsedType(id);
    }
    //下架二手类型
    @Override
    public Integer outUsedType(String id) {
        return usedDao.outUsedType(id);
    }
    //置顶
    @Override
    public Integer stickUsed(String id) {
        return usedDao.stickUsed(id);
    }
    //取消置顶
    @Override
    public Integer cancelStickUsed(String id) {
        return usedDao.cancelStickUsed(id);
    }
    //上架
    @Override
    public Integer upUsed(String id) {
        return usedDao.upUsed(id);
    }
    //获取二手类型，后台端
    @Override
    public List<UsedLabel> findUsedTypeList() {
        return usedDao.findUsedTypeList();
    }
    //获取商品分类
    @Override
    public List<UsedLabel> finCommodityType() {
        return usedDao.finCommodityType();
    }
}
