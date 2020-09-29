package com.zhangzemin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author zhangzemin
 * @date 2020/9/29 15:31
 */
public class ResponseDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        downloadFileByOutputStream(response);
//        downloadChineseFileByOutputStream(response);
        downloadFileByPrintWriter(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 下载文件，通过OutputStream流
     *
     * @param response
     */
    public void downloadFileByOutputStream(HttpServletResponse response) throws IOException {
        //1.获取要下载的文件的绝对路径
        String realPath = this.getServletContext().getRealPath("/photo/测试.png");
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        //4.获取要下载的文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        //5.创建数据缓冲区
        byte[] buffer = new byte[1024];
        //6.通过response对象获取OutputStream流
        OutputStream out = response.getOutputStream();
        //7.将FileInputStream流写入到buffer缓冲区
        while ((len = in.read(buffer)) > 0) {
            //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * 下载中文文件,中文文件下载时，文件名要经过URL编码，否则会出现文件名乱码
     *
     * @param response
     * @throws IOException
     */
    public void downloadChineseFileByOutputStream(HttpServletResponse response) throws IOException {
        //1.获取要下载的文件的绝对路径
        String realPath = this.getServletContext().getRealPath("/photo/测试.png");
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //4.获取要下载的文件输入流
        InputStream in = new FileInputStream(realPath);
        int len = 0;
        //5.创建数据缓冲区
        byte[] buffer = new byte[1024];
        //6.通过response对象获取OutputStream流
        OutputStream out = response.getOutputStream();
        //7.将FileInputStream流写入到buffer缓冲区
        while ((len = in.read(buffer)) > 0) {
            //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    public void downloadFileByPrintWriter(HttpServletResponse response) throws IOException {
        //1.获取要下载的文件的绝对路径
        String realPath = this.getServletContext().getRealPath("/photo/测试.png");
        //2.获取要下载的文件名
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //4.获取要下载的文件输入流
        FileReader in = new FileReader(realPath);
        int len = 0;
        //5.创建数据缓冲区
        char[] buffer = new char[1024];
        //6.通过response对象获取OutputStream流
        PrintWriter out = response.getWriter();
        //7.将FileInputStream流写入到buffer缓冲区
        while ((len = in.read(buffer)) > 0) {
            //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }
}
