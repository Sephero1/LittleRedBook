package com.LittleRedBook.www.controller;

/*
    用于用户上传个人笔记的Servlet
*/

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

@WebServlet("/noteUploadServlet")
public class NoteUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成上传，将笔记数据写入数据库
        NoteService service = new NoteServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String title=request.getParameter("title");
        String text=request.getParameter("text");
        int areaId=Integer.parseInt(request.getParameter("areaId"));//String转int，使用Integer的方法
        int userId=Integer.parseInt(request.getParameter("userId"));//String转int，使用Integer的方法
        String tag1=request.getParameter("tag1");
        String tag2=request.getParameter("tag2");
        String tag3=request.getParameter("tag3");
        //调用方法
        service.upload(title,text,areaId,tag1,tag2,tag3,userId);
        //将笔记标题存入request域，以传递给jsp页面
        request.setAttribute("title",title);
        //转发到jsp页面
        request.getRequestDispatcher("/uploadSuccess.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
