package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通过request对象获取客户端请求信息
 *
 * @author zhangzemin
 * @date 2020/9/30 14:44
 */
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获得客户机信息
         */
        //得到请求的URL地址
        String requestUrl = request.getRequestURL().toString();
        //得到请求的资源
        String requestUri = request.getRequestURI();
        //得到请求的URL地址中附带的参数
        String queryString = request.getQueryString();
        //得到来访者的IP地址
        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        //得到请求URL地址时使用的方法
        String method = request.getMethod();
        String pathInfo = request.getPathInfo();
        //获取WEB服务器的IP地址
        String localAddr = request.getLocalAddr();
        //获取WEB服务器的主机名
        String localName = request.getLocalName();
        //设置将字符以"UTF-8"编码输出到客户端浏览器
        response.setCharacterEncoding("UTF-8");
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("获取到的客户机信息如下：");
        out.write("<hr/>");
        out.write("请求的URL地址：" + requestUrl);
        out.write("<br/>");
        out.write("请求的资源：" + requestUri);
        out.write("<br/>");
        out.write("请求的URL地址中附带的参数：" + queryString);
        out.write("<br/>");
        out.write("来访者的IP地址：" + remoteAddr);
        out.write("<br/>");
        out.write("来访者的主机名：" + remoteHost);
        out.write("<br/>");
        out.write("使用的端口号：" + remotePort);
        out.write("<br/>");
        out.write("remoteUser：" + remoteUser);
        out.write("<br/>");
        out.write("请求使用的方法：" + method);
        out.write("<br/>");
        out.write("pathInfo：" + pathInfo);
        out.write("<br/>");
        out.write("localAddr：" + localAddr);
        out.write("<br/>");
        out.write("localName：" + localName);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
