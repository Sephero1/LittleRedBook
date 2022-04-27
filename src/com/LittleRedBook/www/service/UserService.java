package com.LittleRedBook.www.service;

/*
    用户服务接口
*/

import com.LittleRedBook.www.pojo.User;

import java.util.List;

public interface UserService {
    //查询所有用户信息
    public List<User> findAll();

    //注册新用户，将用户数据写入数据库，注册成功返回0，用户名重复返回1，邮箱重复返回2
    public int register(String username,String password,String sex,String birthday,String email);

    //用户登录，将其输入的数据与数据库中的数据比对，登录成功返回用户序号,登录失败返回0
    public int login(String username,String password);

    //根据用户序号修改用户个人信息，成功返回0，用户名重复返回1，邮箱重复返回2
    public int modify(int userId,String username,String password,String sex,String birthday,String email);

    //方法重载
    //新用户注册：用户名和邮箱查重，如果用户名已存在则返回1，邮箱已存在则返回2，否则返回0
    public int userCheck(String username,String email);
    //修改用户信息：用户名和邮箱查重，如果用户名已存在则返回1，邮箱已存在则返回2，否则返回0
    public int userCheck(int userId,String username,String email);

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
}
