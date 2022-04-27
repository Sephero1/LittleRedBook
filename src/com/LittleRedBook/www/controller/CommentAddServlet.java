package com.LittleRedBook.www.controller;

/*
    用于新增评论的Servlet
*/

import com.LittleRedBook.www.service.CommentService;
import com.LittleRedBook.www.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/commentAddServlet")
public class CommentAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成新增评论
        CommentService commentService=new CommentServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        HttpSession Session=request.getSession();
        int userId=(int)Session.getAttribute("userId");
        String text=request.getParameter("text");
        int objectId=Integer.parseInt(request.getParameter("objectId"));
        int textId=Integer.parseInt(request.getParameter("textId"));
        String title=request.getParameter("title");
        //调用方法
        commentService.add(text,objectId,textId,userId);
        //将数据存入request域中，以转发给Servlet
        request.setAttribute("textId",textId);
        //转发
        request.getRequestDispatcher("/commentFindTextIdServlet?title="+title).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
