package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author zhangzemin
 * @date 2020/10/9 10:56
 */
public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么cookies没有lastAccessTime
        if (null != cookies && checkLastAccessTime(cookies)) {
            out.write("您上次访问的时间是:");
            for (Cookie cookie : cookies) {
                if ("lastAccessTime".equals(cookie.getName())) {
                    long lastAccessTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    out.write(date.toString());
                }
            }
        } else {
            out.write("这是您第一次访问本站！");
        }
        //用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        //创建一个cookie，cookie的名字是lastAccessTime
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        //设置Cookie的有效期为1天
        cookie.setMaxAge(24 * 60 * 60);
        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean checkLastAccessTime(Cookie[] cookies) {
        boolean status = false;
        for (Cookie cookie : cookies) {
            if ("lastAccessTime".equals(cookie.getName())) {
                status = true;
                break;
            }
        }
        return status;
    }
}
