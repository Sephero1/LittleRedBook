package com.LittleRedBook.www.controller;

/*
    用于用户登录的Servlet
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

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service完成登录，将用户数据与数据库中的数据比对
        UserService service = new UserServiceImpl();
        //设置request编码，防止中文乱码
        request.setCharacterEncoding("utf-8");
        //获取方法所需参数
        String username,password;
        HttpSession session=request.getSession();//获取Session
        if("1".equals(request.getParameter("firstLogin"))){//如果是首次登录就从request域中获取数据
            username=request.getParameter("username");
            password=request.getParameter("password");
            //调用方法
            int userId=service.login(username,password);
            //该方法登录成功返回用户序号,登录失败返回0
            if(userId!=0){
                //将用户信息用session保存，以传递给jsp页面
                session.setAttribute("username",username);
                session.setAttribute("userId",userId);
                session.setAttribute("password",password);
                session.setAttribute("rank",service.findRank(userId));//根据用户序号获取用户权限等级
                //转发到jsp页面
                request.getRequestDispatcher("/loginSuccess.jsp").forward(request,response);
            }else{
                //转发到jsp页面
                request.getRequestDispatcher("/loginFailure.jsp").forward(request,response);
            }
        }else{//非首次登录
            //转发到jsp页面
            request.getRequestDispatcher("/loginSuccess.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
