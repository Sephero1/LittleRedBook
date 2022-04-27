package com.LittleRedBook.www.controller;

/*
    查询用户评论过的笔记的Servlet
*/

import com.LittleRedBook.www.pojo.Note;
import com.LittleRedBook.www.service.CommentService;
import com.LittleRedBook.www.service.NoteService;
import com.LittleRedBook.www.service.impl.CommentServiceImpl;
import com.LittleRedBook.www.service.impl.NoteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/userCommentServlet")
public class UserCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service的方法完成查询
        CommentService commentService = new CommentServiceImpl();
        NoteService noteService=new NoteServiceImpl();
        //获取方法所需参数
        HttpSession Session=request.getSession();
        int userId=(int)Session.getAttribute("userId");
        //调用方法
        Set<Integer> set = commentService.findWithUserId(userId);
        List<Note> list=new ArrayList<>();
        for (int i:set) {
            list.add(noteService.findWithId(i));
        }
        //将查询结果存入request域中，以传递给jsp页面
        request.setAttribute("list", list);
        //转发到jsp页面
        request.getRequestDispatcher("/userCommentList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
