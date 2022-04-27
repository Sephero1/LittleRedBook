package com.LittleRedBook.www.controller;

/*
    用于修改用户个人信息的Servlet
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

@WebServlet("/userModifyServlet")
public class UserModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service完成查询
        UserService service=new UserServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        int flag;
        if(!password.equals(password2)){
            flag=3;//两次输入密码不相同的标识
            //将flag存入request域，以传递给jsp页面
            request.setAttribute("flag",flag);
            //转发到jsp页面
            request.getRequestDispatcher("/modifyFailure.jsp").forward(request,response);
        }else{
            String sex=request.getParameter("sex");
            String birthday=request.getParameter("birthday");
            String email=request.getParameter("email");
            int userId=Integer.parseInt(request.getParameter("userId"));//String转int，使用Integer的方法
            //调用方法
            flag=service.modify(userId,username,password,sex,birthday,email);
            //该方法成功返回0，用户名和邮箱重复分别返回1和2
            if(flag==0){
                //将用户信息用session保存，以传递给jsp页面
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                //转发到jsp页面
                request.getRequestDispatcher("/modifySuccess.jsp").forward(request,response);
            }else{
                //将flag存入request域，以传递给jsp页面
                request.setAttribute("flag",flag);
                //转发到jsp页面
                request.getRequestDispatcher("/modifyFailure.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
