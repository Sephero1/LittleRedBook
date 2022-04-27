package com.LittleRedBook.www.controller;

/*
    用于查询笔记的Servlet
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

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成搜索
        NoteService service=new NoteServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String search = request.getParameter("search");
        String object = request.getParameter("object");
        String type = request.getParameter("type");
        //调用方法
        List<Note> list = service.search(search, object, type);
        //将笔记信息存入request域中，以传递给jsp页面
        request.setAttribute("notes",list);
        //转发到jsp页面
        request.getRequestDispatcher("/noteSearchList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
