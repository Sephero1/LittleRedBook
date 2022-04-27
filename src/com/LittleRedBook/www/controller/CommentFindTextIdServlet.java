package com.LittleRedBook.www.controller;

/*
    根据笔记序号查询相关评论的Servlet
*/

import com.LittleRedBook.www.pojo.Comment;
import com.LittleRedBook.www.service.CommentService;
import com.LittleRedBook.www.service.UserService;
import com.LittleRedBook.www.service.impl.CommentServiceImpl;
import com.LittleRedBook.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/commentFindTextIdServlet")
public class CommentFindTextIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        CommentService commentService = new CommentServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        int textId = Integer.parseInt(request.getParameter("textId"));
        //调用方法
        List<Comment> list = commentService.findWithTextId(textId);
        //将查询结果存入request域中，以传递给jsp页面
        request.setAttribute("list", list);
        request.setAttribute("title", request.getParameter("title"));
        request.setAttribute("noteId", textId);
        //转发到jsp页面
        request.getRequestDispatcher("/commentList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
