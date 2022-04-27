package com.LittleRedBook.www.controller;

/*
    用于改变笔记审核状态的Servlet
*/

import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/noteCheckServlet")
public class NoteCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成更新
        NoteService service=new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        int noteId=Integer.parseInt(request.getParameter("noteId"));
        int check=Integer.parseInt(request.getParameter("check"));
        //调用方法
        service.check(check,noteId);
        //转发
        request.getRequestDispatcher("/findUnCheckServlet?rank="+request.getParameter("rank")).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
