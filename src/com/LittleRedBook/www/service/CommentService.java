package com.LittleRedBook.www.service;

/*
    评论服务接口
*/

import com.LittleRedBook.www.pojo.Comment;

import java.util.List;
import java.util.Set;

public interface CommentService {
    //新增评论
    public void add(String text,int objectId,int textId,int userId);

    //查看指定笔记的所有评论
    public List<Comment> findWithTextId(int textId);

    //查看用户评论过的笔记
    public Set<Integer> findWithUserId(int userId);
}
