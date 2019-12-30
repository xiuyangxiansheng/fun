package com.springboot.fun.service;


import com.github.pagehelper.PageInfo;
import com.springboot.fun.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UsedService {

    /*
     * 小程序端
     *
     * */
    //获取二手列表
    PageInfo<Used> finUsedList(int page, int pageSize, Map<String,Object> usedMap);
    //获取二手类型
    List<UsedLabel> selectUsedLabelsType();
    //获取二手标签
    List<UsedLabel> selectUsedLabelsName();
    //获取学校id
    String findSchoolIdByUserId(String userId);
    //用户点赞
    Integer insertUsedLike(String id, String likeUsedId, String likeUserId, String likeUserDoId);
    //给发布二手用户赞加一
    void updateUsedLikeAdd(String likeUsedId);
    //二手发布详情
    List<Used> findUsedById(Map<String,Object> map);
    //点赞列表
    List<UsedLike> findUsedLikeById(String id);
    //评论列表
    List<UsedCommentReply> findUsedCommentById(String id);
    //给浏览量加一
    void updateUsedComplainAdd(String id);
    //根据主键id查询用户id
    String selectLikeUserId(String likeUsedId);
    //获取用户发布所有评论
    PageInfo<UsedComment> findUsedAllComment(int page, int pageSize, String id);
    //给用户评论
    Integer insertUsedComment(UsedComment usedComments);
    //校区
    String selectSchoolName(String usedUserId);
    //发布二手
    Integer insertUsed(String id, String usedUserId, Integer usedType, String usedLable, String usedName,
                       String usedUrl, String usedPrice, String phone,String schoolId,Integer usedPriceState,Integer usedVideoState);
    //查询用户是否已经点过赞了
    Integer selectUsedLikeById(Map<String,Object> likeStateMap);
    //个人中心 我的发布
    PageInfo<Used> findAllSuccessUserList(int page, int pageSize, Map<String,Object> usedMap);
    //给用户回复
    Integer insertUsedCommentRelpy(UsedCommentReply usedCommentReplys);
    //删除评论
    Integer deleteComment(String commentId);
    //删除回复
    Integer deleteCommentReply(String commentReply);
    //下架
    Integer outUsed(String id);
    //投诉
    Integer addComplain(String id);
    //删除
    Integer deleteUsed(String id);
    //获取全部的二手（不分学校）
    PageInfo<Used> finAllUsedList(int page, int pageSize, Map<String,Object> usedMap);
    //获取发布和评论的返回内容
    List<FunUsedState> findUsedState(int type);
    /*后台*/
    //二手筛选列表
    PageInfo<Used> searchUsedList(int page, int pageSize, Map<String,Object> usedMap);
    //给列表刷新的浏览量+1
    void updateUsedComplainListAdd(Map<String,Object> browseMap);
    //二手详情
    Used searchUsedListById(String id);
    //修改二手类型
    Integer updateUsedType(UsedLabel usedLabels);
    //查找类型编号的最大值
    Integer selectCommodityType();
    //添加二手类型
    Integer insertUsedType(UsedLabel usedLabels);
    //评论未看的
    Integer selectCommentSum(String userId);
    //回复未看的
    Integer selectReplaySum(String userId);
    //点击展示未读内容的消息列表
    PageInfo<UsedCommentReplyMessage> selectCommentReplyList(int page, int pageSize, String userId);
    //把评论消息改为已读
    void updateCommentMessage(String userId);
    //把回复消息改为已读
    void updateReplayMessage(String userId);
    //上架二手类型
    Integer upUsedType(String id);
    //下架二手类型
    Integer outUsedType(String id);
    //置顶
    Integer stickUsed(String id);
    //取消置顶
    Integer cancelStickUsed(String id);
    //上架
    Integer upUsed(String id);
    //获取二手类型，后台端
    List<UsedLabel> findUsedTypeList();
    //获取商品分类
    List<UsedLabel> finCommodityType();
}
