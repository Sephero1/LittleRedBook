package com.LittleRedBook.www.controller;

import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/noteModifyServlet")
public class NoteModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成编辑
        NoteService service=new NoteServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String title=request.getParameter("title");
        String text=request.getParameter("text");
        int areaId = Integer.parseInt(request.getParameter("areaId"));//String转int，使用Integer的方法
        String tag1=request.getParameter("tag1");
        String tag2=request.getParameter("tag2");
        String tag3=request.getParameter("tag3");
        int noteId=Integer.parseInt(request.getParameter("noteId"));//String转int，使用Integer的方法
        //调用方法
        service.modify(title,text,areaId,tag1,tag2,tag3,noteId);
        //转发到jsp页面
        request.getRequestDispatcher("/noteModifySuccess.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
