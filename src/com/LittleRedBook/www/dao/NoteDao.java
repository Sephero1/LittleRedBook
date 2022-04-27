package com.LittleRedBook.www.dao;

/*
    笔记数据库操作接口
*/

import com.LittleRedBook.www.pojo.Note;

import java.util.List;

public interface NoteDao {
    //上传笔记，将笔记相关内容写入笔记表
    public void upload(String title,String text,int areaId,String tag1,String tag2,String tag3,int userId);

    //查询指定分区的笔记信息,0代表所有笔记
    public List<Note> find(int areaId);

    //根据搜索内容search搜索笔记，搜索对象object为标签tag或标题title，搜索方式type为精确搜索precise或模糊搜索fuzzy
    public List<Note> search(String search,String object,String type);

    //根据用户序号查询笔记
    public List<Note> view(int userId);

    //编辑笔记，将笔记相关内容写入笔记表
    public void modify(String title,String text,int areaId,String tag1,String tag2,String tag3,int noteId);

    //根据笔记序号删除笔记
    public void delete(int noteId);

    //将用户点赞或收藏的笔记的点赞数或收藏数加一，type为1是点赞，type为2是收藏
    public void likeFavorite(int noteId,int type);

    //将用户取消点赞或收藏的笔记的点赞数或收藏数减一，type为1是点赞，type为2是收藏
    public void unLikeFavorite(int noteId,int type);

    //根据笔记序号查询笔记
    public Note findWithId(int noteId);

    //查询指定分区未审核的笔记
    public List<Note> findUnCheck(int areaId);

    //改变指定笔记的审核状态
    public void check(int check,int noteId);
}
