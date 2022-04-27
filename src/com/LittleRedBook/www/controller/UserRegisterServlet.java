package com.LittleRedBook.www.controller;

/*
    用于注册新用户，将用户数据写入数据库的Servlet
*/

import com.LittleRedBook.www.service.UserService;
import com.LittleRedBook.www.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成注册，将用户数据写入数据库
        UserService service = new UserServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        int flag;
        if(!password.equals(password2))
        {
            flag=3;//两次输入的密码不相同的标识
            //将flag存入request域，以传递给jsp页面
            request.setAttribute("flag",flag);
            //转发到jsp页面
            request.getRequestDispatcher("/registerFailure.jsp").forward(request,response);
        }else{
            String sex=request.getParameter("sex");
            String birthday=request.getParameter("birthday");
            String email=request.getParameter("email");
            //调用方法
            flag=service.register(username,password,sex,birthday,email);
            //该方法注册成功返回0，用户名和邮箱重复分别返回1和2
            if(flag==0){
                //转发到jsp页面
                request.getRequestDispatcher("/registerSuccess.jsp").forward(request,response);
            }else{
                //将flag存入request域，以传递给jsp页面
                request.setAttribute("flag",flag);
                //转发到jsp页面
                request.getRequestDispatcher("/registerFailure.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
