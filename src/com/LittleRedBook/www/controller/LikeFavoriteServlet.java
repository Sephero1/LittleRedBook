package com.LittleRedBook.www.controller;

/*
    用于点赞收藏的Servlet
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/likeFavoriteServlet")
public class LikeFavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成点赞或收藏
        UserService userService = new UserServiceImpl();
        NoteService noteService = new NoteServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        HttpSession Session = request.getSession();
        int userId = (Integer) Session.getAttribute("userId");
        int noteId = Integer.parseInt(request.getParameter("noteId"));
        int type = Integer.parseInt(request.getParameter("type"));
        //调用方法
        List<Integer> Integers = userService.likeFavorite(userId, noteId, type);
        noteService.likeFavorite(noteId, type);
        //遍历列表，如果已点赞或收藏，flag=true
        boolean flag = false;
        for (Integer i : Integers) {
            if (i == noteId) {
                flag = true;
            } else {
                flag = false;
            }
        }
        //将笔记信息存入request域，以传递给jsp页面
        int likes, favorites;
        if (type == 1) {//type=1为点赞
            request.setAttribute("likeFlag", flag);
            request.setAttribute("favoriteFlag", request.getParameter("favoriteFlag"));
            likes = Integer.parseInt(request.getParameter("likes")) + 1;
            favorites = Integer.parseInt(request.getParameter("favorites"));
        } else {//type=2为收藏
            request.setAttribute("favoriteFlag", flag);
            request.setAttribute("likeFlag", request.getParameter("likeFlag"));
            likes = Integer.parseInt(request.getParameter("likes"));
            favorites = Integer.parseInt(request.getParameter("favorites")) + 1;
        }
        request.setAttribute("title", request.getParameter("title"));//还未获取过的需要从request中获取出来
        request.setAttribute("text", request.getParameter("text"));
        request.setAttribute("likes", likes);
        request.setAttribute("favorites", favorites);
        request.setAttribute("noteId", noteId);
        //转发到jsp页面
        request.getRequestDispatcher("/noteDetail.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
