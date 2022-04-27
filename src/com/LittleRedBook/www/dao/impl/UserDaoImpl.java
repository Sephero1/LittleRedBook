package com.LittleRedBook.www.dao.impl;

/*
    用户数据库操作实现类
*/

import com.LittleRedBook.www.dao.UserDao;
import com.LittleRedBook.www.pojo.User;
import com.LittleRedBook.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    //查询所有用户信息
    public List<User> findAll() {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的 封装了查询结果类的 集合
        List<User> ret = new ArrayList<>();

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "select * from user";
            //预编译sql
            st = conn.prepareStatement(sql);
            //执行sql
            rs = st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUserName(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setSex(rs.getString("sex"));
                u.setBirthday(rs.getString("birthday"));
                u.setEmail(rs.getString("email"));
                u.setRank(rs.getInt("rank"));
                u.setBlacklist(rs.getString("blacklist"));
                u.setLikes(rs.getString("likes"));
                u.setFavorites(rs.getString("favorites"));
                ret.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }

    @Override
    //注册新用户，将用户数据写入数据库
    public void register(String username, String password, String sex, String birthday, String email) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "insert into user(username,`password`,sex,birthday,email,`rank`,blacklist,likes,favorites) values(?,MD5(?),?,?,?,'0','0,','0,','0,')";
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, sex);
            st.setString(4, birthday);
            st.setString(5, email);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
    }

    @Override
    //根据用户序号修改用户个人信息
    public void modify(int userId, String username, String password, String sex, String birthday, String email) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "update user set username=?,`password`=MD5(?),sex=?,birthday=?,email=? where id=?";
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, sex);
            st.setString(4, birthday);
            st.setString(5, email);
            st.setInt(6, userId);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
    }

    @Override
    //将用户点赞或收藏的笔记序号加入到String中，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> likeFavorite(int userId, int noteId, int type) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的 封装了查询结果类的 集合
        List<Integer> ret = new ArrayList<>();

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql;
            if (type == 1) {//type为1是点赞
                sql = "select likes from user where id=?";
            } else {//type为2是收藏
                sql = "select favorites from user where id=?";
            }
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1, userId);
            //执行sql
            rs = st.executeQuery();
            //将结果集中的字符串取出
            String likeFavorite;
            rs.next();
            if (type == 1) {//type为1是点赞
                likeFavorite = rs.getString("likes");
            } else {//type为2是收藏
                likeFavorite = rs.getString("favorites");
            }
            //将字符串以逗号分割成字符串数组
            String[] ss = likeFavorite.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                ret.add(Integer.valueOf(ss[i]));//将已有的值加入用于返回的集合
                sb.append(ss[i]).append(",");//将已有的值拼接进字符串
            }
            ret.add(noteId);//将新增的值加入用于返回的集合
            sb.append("" + noteId).append(",");//将新增的值拼接进字符串
            likeFavorite = sb.toString();//StringBuilder转String
            //编写sql
            if (type == 1) {//type为1是点赞
                sql = "update user set likes=? where id=?";
            } else {//type为2是收藏
                sql = "update user set favorites=? where id=?";
            }
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1, likeFavorite);
            st.setInt(2, userId);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }

    @Override
    //查询用户是否已点赞或收藏，type为1是点赞，type为2是收藏，返回点赞或收藏的noteId的List集合
    public List<Integer> isLikeFavorite(int userId, int type) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的 封装了查询结果类的 集合
        List<Integer> ret = new ArrayList<>();

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql;
            if (type == 1) {//type为1是点赞
                sql = "select likes from user where id=?";
            } else {//type为2是收藏
                sql = "select favorites from user where id=?";
            }
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1, userId);
            //执行sql
            rs = st.executeQuery();
            //将结果集中的字符串取出
            String likeFavorite;
            rs.next();
            if (type == 1) {//type为1是点赞
                likeFavorite = rs.getString("likes");
            } else {//type为2是收藏
                likeFavorite = rs.getString("favorites");
            }
            //将字符串以逗号分割成字符串数组
            String[] ss = likeFavorite.split(",");
            for (int i = 0; i < ss.length; i++) {
                ret.add(Integer.valueOf(ss[i]));//将已有的值加入用于返回的集合
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }

    @Override
    //将用户取消点赞或收藏的笔记序号从String中删除，type为1是点赞，type为2是收藏，返回String转为的List集合
    public List<Integer> unLikeFavorite(int userId, int noteId, int type) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的 封装了查询结果类的 集合
        List<Integer> ret = new ArrayList<>();

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql;
            if (type == 1) {//type为1是点赞
                sql = "select likes from user where id=?";
            } else {//type为2是收藏
                sql = "select favorites from user where id=?";
            }
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1, userId);
            //执行sql
            rs = st.executeQuery();
            //将结果集中的字符串取出
            String likeFavorite;
            rs.next();
            if (type == 1) {//type为1是点赞
                likeFavorite = rs.getString("likes");
            } else {//type为2是收藏
                likeFavorite = rs.getString("favorites");
            }
            //将字符串以逗号分割成字符串数组
            String[] ss = likeFavorite.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                if(Integer.valueOf(ss[i])!=noteId){//排除要删除的笔记序号
                    ret.add(Integer.valueOf(ss[i]));//将已有的值加入用于返回的集合
                    sb.append(ss[i]).append(",");//将已有的值拼接进字符串
                }
            }
            likeFavorite = sb.toString();//StringBuilder转String
            //编写sql
            if (type == 1) {//type为1是点赞
                sql = "update user set likes=? where id=?";
            } else {//type为2是收藏
                sql = "update user set favorites=? where id=?";
            }
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1, likeFavorite);
            st.setInt(2, userId);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }

    @Override
    //根据用户序号查询用户名
    public String findNameWithId(int userId) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的字符串
        String userName=null;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "select userName from user where id=?";
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1, userId);
            //执行sql
            rs = st.executeQuery();
            //将查询结果赋值给用于返回的字符串
            rs.next();
            userName=rs.getString("userName");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回字符串
        return userName;
    }

    @Override
    //根据用户序号查询权限等级
    public int findRank(int userId) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的权限等级int
        int ret=0;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "select `rank` from user where id=?";
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1, userId);
            //执行sql
            rs = st.executeQuery();
            //将查询结果赋值给用于返回的int
            rs.next();
            ret=rs.getInt("rank");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回权限等级int
        return ret;
    }

    @Override
    //根据用户输入的密码返回MD5加密后的值
    public String MD5(String password) {
        //数据库对象
        Connection conn = null;
        //执行sql的对象
        PreparedStatement st = null;
        //查询结果集
        ResultSet rs = null;
        //用于返回的加密后的密码
        String ret=null;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //编写sql
            String sql = "select MD5(?)";
            //预编译sql
            st = conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1, password);
            //执行sql
            rs = st.executeQuery();
            //将查询结果赋值给用于返回的字符串
            rs.next();
            ret=rs.getString("MD5('"+password+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            JdbcUtil.release(conn, st, rs);
        }
        //返回加密后的密码
        return ret;
    }
}
