package com.LittleRedBook.www.controller;

/*
    用于笔记链接列表的Servlet
*/

import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.pojo.User;
import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.UserService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;
import com.LittleRedBook.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/noteListServlet")
public class NoteListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        NoteService service=new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        int areaId = Integer.parseInt(request.getParameter("areaId"));
        //调用方法
        List<Note> notes = service.find(areaId);
        //将查询结果存入request域
        request.setAttribute("notes",notes);
        //转发到jsp页面
        request.getRequestDispatcher("/noteList2.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
