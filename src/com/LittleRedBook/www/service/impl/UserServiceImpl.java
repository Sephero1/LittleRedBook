package com.LittleRedBook.www.service.impl;

/*
    用户服务实现类
*/

import com.LittleRedBook.www.dao.UserDao;
import com.LittleRedBook.www.dao.impl.UserDaoImpl;
import com.LittleRedBook.www.pojo.User;
import com.LittleRedBook.www.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //创建数据库操作对象
    private UserDao dao = new UserDaoImpl();

    @Override
    //查询所有用户信息
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    //注册新用户，将用户数据写入数据库，注册成功返回0，用户名重复返回1，邮箱重复返回2
    public int register(String username, String password, String sex, String birthday, String email) {
        //调用方法对用户名和邮箱进行查重
        int flag = this.userCheck(username, email);
        if (flag == 0) {
            dao.register(username, password, sex, birthday, email);//注册新用户，将用户数据写入数据库
            return flag;//注册成功返回0
        } else {
            return flag;//用户名重复返回1，邮箱重复返回2
        }
    }

    @Override
    //用户登录，将其输入的数据与数据库中的数据比对，登录成功返回用户序号,登录失败返回0
    public int login(String username, String password) {
        List<User> list = dao.findAll();
        for (User user : list) {
            if (username.equals(user.getUserName()) && dao.MD5(password).equals(user.getPassword())) {
                return user.getId();
            }
        }
        return 0;
    }

    @Override
    //根据用户序号修改用户个人信息，成功返回0，用户名重复返回1，邮箱重复返回2
    public int modify(int userId, String username, String password, String sex, String birthday, String email) {
        //调用方法对用户名和邮箱进行查重
        int flag = this.userCheck(userId,username, email);
        if (flag == 0) {
            dao.modify(userId, username, password, sex, birthday, email);//根据用户序号修改用户个人信息
            return flag;//成功返回0
        } else {
            return flag;//用户名重复返回1，邮箱重复返回2
        }
    }

    @Override
    //新用户注册：用户名和邮箱查重，如果用户名已存在则返回1，邮箱已存在则返回2，否则返回0
    public int userCheck(String username, String email) {
        //调用方法获取用户信息
        List<User> users = dao.findAll();
        //遍历已有用户的信息
        for (User user : users) {
            if (username.equals(user.getUserName())) {
                return 1;//如果用户名已存在则返回1
            } else if (email.equals(user.getEmail())) {
                return 2;//邮箱已存在则返回2
            }
        }
        return 0;//否则返回0
    }

    @Override
    //修改用户信息：用户名和邮箱查重，如果用户名已存在则返回1，邮箱已存在则返回2，否则返回0
    public int userCheck(int userId, String username, String email) {
        //调用方法获取用户信息
        List<User> users = dao.findAll();
        //遍历已有用户的信息
        for (User user : users) {
            if (userId!=user.getId()&&username.equals(user.getUserName())) {
                return 1;//如果用户名已存在则返回1
            } else if (userId!=user.getId()&&email.equals(user.getEmail())) {
                return 2;//邮箱已存在则返回2
            }
        }
        return 0;//否则返回0
    }

    @Override
    //将用户点赞或收藏的笔记序号加入到String中，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> likeFavorite(int userId, int noteId, int type) {
        return dao.likeFavorite(userId,noteId,type);
    }

    @Override
    //查询用户是否已点赞或收藏，type为1是点赞，type为2是收藏，返回点赞或收藏的noteId的List集合
    public List<Integer> isLikeFavorite(int userId, int type) {
        return dao.isLikeFavorite(userId,type);
    }

    @Override
    //将用户取消点赞或收藏的笔记序号从String中删除，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> unLikeFavorite(int userId, int noteId, int type) {
        return dao.unLikeFavorite(userId, noteId, type);
    }

    @Override
    //根据用户序号查询用户名
    public String findNameWithId(int userId) {
        return dao.findNameWithId(userId);
    }

    @Override
    //根据用户序号查询权限等级
    public int findRank(int userId) {
        return dao.findRank(userId);
    }
}
