package com.LittleRedBook.www.controller;

/*
    用于用户查看个人笔记的Servlet
*/

import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/noteViewServlet")
public class NoteViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        NoteService service=new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        int userId=Integer.parseInt(request.getParameter("userId"));
        List<Note> notes = service.view(userId);
        //将查询结果存入request域
        request.setAttribute("notes",notes);
        //转发到jsp页面
        request.getRequestDispatcher("/noteViewList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
