package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzemin
 * @date 2020/9/30 14:24
 */
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收客户端浏览器提交上来的验证码
        String clientCheckcode = request.getParameter("validateCode");
        //从服务器端的session中取出验证码
        String serverCheckcode = (String) request.getSession().getAttribute("checkcode");
        //将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
        if (clientCheckcode.equals(serverCheckcode)) {
            System.out.println("验证码验证通过！");
        } else {
            System.out.println("验证码验证失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
