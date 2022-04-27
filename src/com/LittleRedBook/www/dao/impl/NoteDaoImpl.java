package com.LittleRedBook.www.dao.impl;

/*
    笔记数据库操作实现类
*/

import com.LittleRedBook.www.dao.NoteDao;
import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl implements NoteDao {

    @Override
    //上传笔记，将笔记相关内容写入笔记表
    public void upload(String title, String text, int areaId, String tag1, String tag2, String tag3,int userId) {
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
            String sql="insert into note(title,text,areaId,likes,favorites,comments,tag1,tag2,tag3,`check`,userId) values(?,?,?,'0','0','0',?,?,?,'0',?)";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1,title);
            st.setString(2,text);
            st.setInt(3,areaId);
            st.setString(4,tag1);
            st.setString(5,tag2);
            st.setString(6,tag3);
            st.setInt(7,userId);
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
    //查询指定分区的笔记信息,0代表所有笔记
    public List<Note> find(int areaId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        List<Note> ret=new ArrayList<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select * from note where areaId in (?,?,?,?)&&`check`=1";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            if(areaId==0||areaId==5){
                st.setInt(1,1);
                st.setInt(2,2);
                st.setInt(3,3);
                st.setInt(4,4);
            }else{
                st.setInt(1,areaId);
                st.setInt(2,areaId);
                st.setInt(3,areaId);
                st.setInt(4,areaId);
            }
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                Note n=new Note();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setAreaId(rs.getInt("areaId"));
                n.setLikes(rs.getInt("likes"));
                n.setFavorites(rs.getInt("favorites"));
                n.setComments(rs.getInt("comments"));
                n.setTag1(rs.getString("tag1"));
                n.setTag2(rs.getString("tag2"));
                n.setTag3(rs.getString("tag3"));
                n.setCheck(rs.getInt("check"));
                n.setUserId(rs.getInt("userId"));
                ret.add(n);
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
    //根据搜索内容search搜索笔记，搜索对象object为标签tag或标题title，搜索方式type为精确搜索precise或模糊搜索fuzzy
    public List<Note> search(String search, String object, String type) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        List<Note> ret=new ArrayList<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //根据搜索对象object为标签tag或标题title，搜索方式type为精确搜索precise或模糊搜索fuzzy，分别编写sql
            String sql;
            if("title".equals(object)){
                if("precise".equals(type)){
                    sql="select * from note where title=?&&`check`=1";
                }else{
                    sql="select * from note where title like \"%\"?\"%\"&&`check`=1";
                }
            }else{
                if("precise".equals(type)){
                    sql="select * from note where (tag1=?||tag2=?||tag3=?)&&`check`=1";
                }else{
                    sql="select * from note where (tag1 like \"%\"?\"%\"||tag2 like \"%\"?\"%\"||tag3 like \"%\"?\"%\")&&`check`=1";
                }
            }
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            if("title".equals(object)){
                st.setString(1,search);
            }else{
                st.setString(1,search);
                st.setString(2,search);
                st.setString(3,search);
            }
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                Note n=new Note();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setAreaId(rs.getInt("areaId"));
                n.setLikes(rs.getInt("likes"));
                n.setFavorites(rs.getInt("favorites"));
                n.setComments(rs.getInt("comments"));
                n.setTag1(rs.getString("tag1"));
                n.setTag2(rs.getString("tag2"));
                n.setTag3(rs.getString("tag3"));
                n.setCheck(rs.getInt("check"));
                n.setUserId(rs.getInt("userId"));
                ret.add(n);
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
    //根据用户序号查询笔记
    public List<Note> view(int userId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        List<Note> ret=new ArrayList<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select * from note where userId=?&&`check`=1";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,userId);
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                Note n=new Note();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setAreaId(rs.getInt("areaId"));
                n.setLikes(rs.getInt("likes"));
                n.setFavorites(rs.getInt("favorites"));
                n.setComments(rs.getInt("comments"));
                n.setTag1(rs.getString("tag1"));
                n.setTag2(rs.getString("tag2"));
                n.setTag3(rs.getString("tag3"));
                n.setCheck(rs.getInt("check"));
                n.setUserId(rs.getInt("userId"));
                ret.add(n);
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
    //编辑笔记，将笔记相关内容写入笔记表
    public void modify(String title, String text, int areaId, String tag1, String tag2, String tag3, int noteId) {
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
            String sql="update note set title=?,text=?,areaId=?,tag1=?,tag2=?,tag3=?,`check`=0 where id=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setString(1,title);
            st.setString(2,text);
            st.setInt(3,areaId);
            st.setString(4,tag1);
            st.setString(5,tag2);
            st.setString(6,tag3);
            st.setInt(7,noteId);
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
    //根据笔记序号删除笔记
    public void delete(int noteId) {
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
            String sql="delete from note where id=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,noteId);
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
    //将用户点赞或收藏的笔记的点赞数或收藏数加一，type为1是点赞，type为2是收藏
    public void likeFavorite(int noteId, int type) {
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
            String sql;
            if(type==1) {//type为1是点赞
                sql="update note set likes=likes+1 where id=?";
            }else{//type为2是收藏
                sql="update note set favorites=favorites+1 where id=?";
            }
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,noteId);
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
    //将用户取消点赞或收藏的笔记的点赞数或收藏数减一，type为1是点赞，type为2是收藏
    public void unLikeFavorite(int noteId, int type) {
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
            String sql;
            if(type==1) {//type为1是点赞
                sql="update note set likes=likes-1 where id=?";
            }else{//type为2是收藏
                sql="update note set favorites=favorites-1 where id=?";
            }
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,noteId);
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
    //根据笔记序号查询笔记
    public Note findWithId(int noteId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的Note对象
        Note n=new Note();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select * from note where id=?&&`check`=1";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,noteId);
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装到对象中
            while(rs.next()){
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setAreaId(rs.getInt("areaId"));
                n.setLikes(rs.getInt("likes"));
                n.setFavorites(rs.getInt("favorites"));
                n.setComments(rs.getInt("comments"));
                n.setTag1(rs.getString("tag1"));
                n.setTag2(rs.getString("tag2"));
                n.setTag3(rs.getString("tag3"));
                n.setCheck(rs.getInt("check"));
                n.setUserId(rs.getInt("userId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放连接资源
            JdbcUtil.release(conn,st,rs);
        }
        //返回封装了数据的对象
        return n;
    }

    @Override
    //查询指定分区未审核的笔记
    public List<Note> findUnCheck(int areaId) {
        //数据库对象
        Connection conn=null;
        //执行sql的对象
        PreparedStatement st=null;
        //查询结果集
        ResultSet rs=null;
        //用于返回的 封装了查询结果类的 集合
        List<Note> ret=new ArrayList<>();

        try {
            //获取连接
            conn= JdbcUtil.getConnection();
            //编写sql
            String sql="select * from note where `check`=0&&areaId=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,areaId);
            //执行sql
            rs=st.executeQuery();
            //遍历结果集，将数据封装后加入到集合中
            while(rs.next()){
                Note n=new Note();
                n.setId(rs.getInt("id"));
                n.setTitle(rs.getString("title"));
                n.setText(rs.getString("text"));
                n.setAreaId(rs.getInt("areaId"));
                n.setLikes(rs.getInt("likes"));
                n.setFavorites(rs.getInt("favorites"));
                n.setComments(rs.getInt("comments"));
                n.setTag1(rs.getString("tag1"));
                n.setTag2(rs.getString("tag2"));
                n.setTag3(rs.getString("tag3"));
                n.setCheck(rs.getInt("check"));
                n.setUserId(rs.getInt("userId"));
                ret.add(n);
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
    //改变指定笔记的审核状态
    public void check(int check, int noteId) {
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
            String sql="update note set `check`=? where id=?";
            //预编译sql
            st=conn.prepareStatement(sql);
            //给sql参数赋值
            st.setInt(1,check);
            st.setInt(2,noteId);
            //执行sql
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放连接资源
            JdbcUtil.release(conn,st,rs);
        }
    }
}
