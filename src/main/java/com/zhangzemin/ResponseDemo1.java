package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author zhangzemin
 * @date 2020/9/29 11:14
 */
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        outputChineseByOutputStream(response);
//        outputChineseByPrintWriter(response);
//        outputOneByOutputStream(response);
        outputOneByPrintWriter(response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 使用OutputStream流输出中文
     *
     * @param response
     * @throws IOException
     */
    public void outputChineseByOutputStream(HttpServletResponse response) throws IOException {
        /**
         * 使用OutputStream输出中文注意问题：
         * 在服务器端，数据是以哪个码表输出的，那么就要控制客户端浏览器以相应的码表打开，
         * 比如：outputStream.write("中国".getBytes("UTF-8"));//使用OutputStream流向客户端浏览器输出中文，以UTF-8的编码进行输出
         * 此时就要控制客户端浏览器以UTF-8的编码打开，否则显示的时候就会出现中文乱码，那么在服务器端如何控制客户端浏览器以以UTF-8的编码显示数据呢？
         * 可以通过设置响应头控制浏览器的行为，例如：
         * response.setHeader("content-type", "text/html;charset=UTF-8");//通过设置响应头控制浏览器以UTF-8的编码显示数据
         */
        String data = "中国";
        //获取OutputStream输出流
        OutputStream outputStream = response.getOutputStream();
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8");
        /**
         * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
         * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表
         * 如果是中文的操作系统环境，默认就是查找查GB2312的码表，
         * 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
         * 比如："中"在GB2312的码表上对应的数字是98,"国"在GB2312的码表上对应的数字是99
         */
        //将字符转换成字节数组，指定以UTF-8编码进行转换
        byte[] dataBytes = data.getBytes("UTF-8");
        //使用OutputStream流向客户端输出字节数组
        outputStream.write(dataBytes);
    }

    /**
     * 使用PrintWriter流输出中文
     *
     * @param response
     */
    public void outputChineseByPrintWriter(HttpServletResponse response) throws IOException {
        String data = "中国";
        //设置将字符以"UTF-8"编码输出到客户端浏览器,如果不加则显示乱码
        response.setCharacterEncoding("UTF-8");
        //获取PrintWriter输出流
        PrintWriter printWriter = response.getWriter();
        printWriter.write(data);
    }

    /**
     * 使用OutputStream流输出数字1
     *
     * @param response
     * @throws IOException
     */
    public void outputOneByOutputStream(HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write("使用OutputStream流输出数字1：".getBytes("UTF-8"));
//        outputStream.write(1);
        outputStream.write((1 + "").getBytes("UTF-8"));
    }

    /**
     * 使用PrintWriter流输出数字1
     *
     * @param response
     * @throws IOException
     */
    public void outputOneByPrintWriter(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("使用PrintWriter流输出数字1：");
        out.write(1 + "");
    }

}
