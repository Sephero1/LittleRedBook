package com.LittleRedBook.www.controller;

/*
    查询所有用户信息的Servlet
*/

import com.LittleRedBook.www.pojo.User;
import com.LittleRedBook.www.service.UserService;
import com.LittleRedBook.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        UserService service=new UserServiceImpl();
        List<User> users = service.findAll();
        //将查询结果存入request域
        request.setAttribute("users",users);
        //转发到jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
