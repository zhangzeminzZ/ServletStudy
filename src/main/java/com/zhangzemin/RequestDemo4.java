package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzemin
 * @date 2020/9/30 16:47
 */
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        System.out.println("username:" + userName);
        System.out.println(name);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 客户端是以UTF-8编码传输数据到服务器端的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
         */
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        System.out.println("username:" + userName);
    }
}
