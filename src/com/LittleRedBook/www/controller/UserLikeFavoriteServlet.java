package com.LittleRedBook.www.controller;

/*
    查询个人点赞或收藏的笔记的Servlet
*/

import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.UserService;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;
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

@WebServlet("/userLikeFavoriteServlet")
public class UserLikeFavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        UserService userService=new UserServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        HttpSession Session=request.getSession();
        int userId=(Integer) Session.getAttribute("userId");
        int type = Integer.parseInt(request.getParameter("type"));
        //调用方法，将查询的笔记信息存入集合中
        List<Integer> Integers = userService.isLikeFavorite(userId,type);
        List<Note> notes=new ArrayList<>();
        for (int i:Integers) {
            notes.add(noteService.findWithId(i));
        }
        //将查询结果存入request域
        request.setAttribute("notes",notes);
        request.setAttribute("type",type);
        //转发到jsp页面
        request.getRequestDispatcher("/likeFavoriteList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
