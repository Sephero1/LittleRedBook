package com.LittleRedBook.www.dao.impl;

/*
    评论数据库操作实现类
*/

import com.LittleRedBook.www.dao.CommentDao;
import com.LittleRedBook.www.pojo.Comment;
import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommentDaoImpl implements CommentDao {
    @Override
    //新增评论
    public void add(String text, int objectId, int textId, int userId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="insert into comment(text,objectId,textId,userId) values(?,?,?,?)";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1,text);
            st.setInt(2,objectId);
            st.setInt(3,textId);
            st.setInt(4,userId);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放连接资源
            JdbcUtil.release(conn,st,rs);
        }
    }

    @Override
    //查看指定笔记的所有评论
    public List<Comment> findWithTextId(int textId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        List<Comment> ret=new ArrayList<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select * from comment where textId=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,textId);
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                Comment c=new Comment();
                c.setId(rs.getInt("id"));
                c.setText(rs.getString("text"));
                c.setObjectId(rs.getInt("objectId"));
                c.setTextId(textId);
                c.setUserId(rs.getInt("userId"));
                ret.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放连接资源
            JdbcUtil.release(conn,st,rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }

    @Override
    //查看用户评论过的笔记
    public Set<Integer> findWithUserId(int userId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        Set<Integer> ret=new HashSet<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select textId from comment where userId=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,userId);
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                ret.add(rs.getInt("textId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放连接资源
            JdbcUtil.release(conn,st,rs);
        }
        //返回封装了查询结果类的集合
        return ret;
    }
}
