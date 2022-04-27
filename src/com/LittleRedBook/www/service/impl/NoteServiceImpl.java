package com.LittleRedBook.www.service.impl;

/*
    笔记服务实现类
*/

import com.LittleRedBook.www.dao.NoteDao;
import com.LittleRedBook.www.dao.impl.NoteDaoImpl;
import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.service.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {

    //创建数据库操作对象
    private NoteDao dao = new NoteDaoImpl();

    @Override
    //上传笔记，将笔记相关内容写入笔记表
    public void upload(String title, String text, int areaId, String tag1, String tag2, String tag3, int userId) {
        dao.upload(title, text, areaId, tag1, tag2, tag3, userId);
    }

    @Override
    //查询指定分区的笔记信息,0代表所有笔记
    public List<Note> find(int areaId) {
        return dao.find(areaId);
    }

    @Override
    //根据搜索内容search搜索笔记，搜索对象object为标签tag或标题title，搜索方式type为精确搜索precise或模糊搜索fuzzy
    public List<Note> search(String search, String object, String type) {
        return dao.search(search,object,type);
    }

    @Override
    //根据用户序号查询笔记
    public List<Note> view(int userId) {
        return dao.view(userId);
    }

    @Override
    //编辑笔记，将笔记相关内容写入笔记表
    public void modify(String title, String text, int areaId, String tag1, String tag2, String tag3, int noteId) {
        dao.modify(title,text,areaId,tag1,tag2,tag3,noteId);
    }

    @Override
    //根据笔记序号删除笔记
    public void delete(int noteId) {
        dao.delete(noteId);
    }

    @Override
    //将用户点赞或收藏的笔记的点赞数或收藏数加一，type为1是点赞，type为2是收藏
    public void likeFavorite(int noteId, int type) {
        dao.likeFavorite(noteId,type);
    }

    @Override
    //将用户取消点赞或收藏的笔记的点赞数或收藏数减一，type为1是点赞，type为2是收藏
    public void unLikeFavorite(int noteId, int type) {
        dao.unLikeFavorite(noteId,type);
    }

    @Override
    //根据笔记序号查询笔记
    public Note findWithId(int noteId) {
        return dao.findWithId(noteId);
    }

    @Override
    //查询指定分区未审核的笔记
    public List<Note> findUnCheck(int areaId) {
        return dao.findUnCheck(areaId);
    }

    @Override
    //改变指定笔记的审核状态
    public void check(int check, int noteId) {
        dao.check(check, noteId);
    }
}
