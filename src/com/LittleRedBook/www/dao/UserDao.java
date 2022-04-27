package com.LittleRedBook.www.dao;

/*
    用户数据库操作接口
*/

import com.LittleRedBook.www.pojo.User;

import java.util.List;

public interface UserDao {
    //查询所有用户信息
    public List<User> findAll();

    //注册新用户，将用户数据写入数据库
    public void register(String username,String password,String sex,String birthday,String email);

    //根据用户序号修改用户个人信息
    public void modify(int userId,String username,String password,String sex,String birthday,String email);

    //将用户点赞或收藏的笔记序号加入到String中，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> likeFavorite(int userId,int noteId,int type);

    //查询用户是否已点赞或收藏，type为1是点赞，type为2是收藏，返回点赞或收藏的noteId的List集合
    public List<Integer> isLikeFavorite(int userId,int type);

    //将用户取消点赞或收藏的笔记序号从String中删除，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> unLikeFavorite(int userId,int noteId,int type);

    //根据用户序号查询用户名
    public String findNameWithId(int userId);

    //根据用户序号查询权限等级
    public int findRank(int userId);

    //根据用户输入的密码返回MD5加密后的值
    public String MD5(String password);
}
