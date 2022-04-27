package com.LittleRedBook.www.service.impl;

/*
    评论服务实现类
*/

import com.LittleRedBook.www.dao.CommentDao;
import com.LittleRedBook.www.dao.impl.CommentDaoImpl;
import com.LittleRedBook.www.pojo.Comment;
import com.LittleRedBook.www.service.CommentService;

import java.util.List;
import java.util.Set;

public class CommentServiceImpl implements CommentService {

    //创建数据库操作对象
    private CommentDao dao = new CommentDaoImpl();

    @Override
    //新增评论
    public void add(String text, int objectId, int textId, int userId) {
        dao.add(text, objectId, textId, userId);
    }

    @Override
    //查看指定笔记的所有评论
    public List<Comment> findWithTextId(int textId) {
        return dao.findWithTextId(textId);
    }

    @Override
    //查看用户评论过的笔记
    public Set<Integer> findWithUserId(int userId) {
        return dao.findWithUserId(userId);
    }
}
