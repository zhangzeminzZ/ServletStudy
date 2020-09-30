package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzemin
 * @date 2020/9/30 17:29
 */
public class RequestDemo5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data="大家好，我是张泽民，我正在总结JavaWeb";
        /**
         * 将数据存放到request对象中,此时把request对象当作一个Map容器来使用
         */
        request.setAttribute("data",data);
        //客户端访问RequestDemo5这个Servlet后，RequestDemo5通知服务器将请求转发(forward)到test.jsp页面进行处理
        request.getRequestDispatcher("/test.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
