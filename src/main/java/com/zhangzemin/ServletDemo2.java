package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangzemin
 * @date 2020/9/21 13:18
 */
public class ServletDemo2 extends HttpServlet {
    int i = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 加了synchronized后，并发访问i时就不存在线程安全问题了，
         * 为什么加了synchronized后就没有线程安全问题了呢？
         * 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
         * 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
         * 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
         */
        synchronized (this) {
            try {
                i++;
                Thread.sleep(1000 * 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.getWriter().write(i + "");
            response.getOutputStream();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
