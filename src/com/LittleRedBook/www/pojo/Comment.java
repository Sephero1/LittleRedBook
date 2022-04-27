package com.LittleRedBook.www.pojo;
/*
    评论类
*/
public class Comment {
    private int id;         //评论序号
    private String text;    //评论内容
    private int objectId;   //评论对象
    private int textId;     //文章序号
    private int userId;     //所属用户序号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", objectId=" + objectId +
                ", textId=" + textId +
                ", userId=" + userId +
                '}';
    }
}
