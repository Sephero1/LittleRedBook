package com.LittleRedBook.www.controller;

/*
    用于查询用户是否已点赞或收藏的Servlet
*/

import com.LittleRedBook.www.service.UserService;

import com.LittleRedBook.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/isLikeFavoriteServlet")
public class isLikeFavoriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        UserService userService=new UserServiceImpl();
        //获取方法所需参数，String转int，使用Integer的方法
        HttpSession Session=request.getSession();
        int userId=(Integer)Session.getAttribute("userId");
        int noteId=Integer.parseInt(request.getParameter("noteId"));
        //是否已点赞？
        int type=1;
        //调用方法
        List<Integer> Integers = userService.isLikeFavorite(userId, type);
        //遍历列表，如果已点赞或收藏，flag=true
        boolean likeFlag=false;
        for (Integer i:Integers) {
            if(i==noteId) {
                likeFlag=true;
            }else{
                likeFlag=false;
            }
        }
        //是否已收藏？
        type=2;
        //调用方法
        Integers = userService.isLikeFavorite(userId, type);
        //遍历列表，如果已点赞或收藏，flag=true
        boolean favoriteFlag=false;
        for (Integer i:Integers) {
            if(i==noteId) {
                favoriteFlag=true;
            }else{
                favoriteFlag=false;
            }
        }
        //将笔记信息存入request域，以传递给jsp页面
        request.setAttribute("likeFlag",likeFlag);
        request.setAttribute("favoriteFlag",favoriteFlag);
        request.setAttribute("title",request.getParameter("title"));//还未获取过的需要从request中获取出来
        request.setAttribute("text",request.getParameter("text"));
        request.setAttribute("likes",request.getParameter("likes"));
        request.setAttribute("favorites",request.getParameter("favorites"));
        request.setAttribute("noteId",noteId);
        //转发到jsp页面
        request.getRequestDispatcher("/noteDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
