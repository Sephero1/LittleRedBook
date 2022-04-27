package com.LittleRedBook.www.controller;

/*
    根据笔记序号删除指定笔记的Servlet
*/

import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/noteDeleteServlet")
public class NoteDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成删除
        NoteService service=new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        int noteId=Integer.parseInt(request.getParameter("noteId"));
        //调用方法
        service.delete(noteId);
        //转发到jsp页面
        request.getRequestDispatcher("/noteDeleteSuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
