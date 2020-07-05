package cn.edu.upc.gsl.service.impl;

import cn.edu.upc.gsl.service.FileService2;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <h3>manage</h3>
 * <p>文件上传下载操作</p>
 * @author : gsl
 * @date : 2020-05-20 12:17
 **/
@Service
public class FileService2Impl implements FileService2 {

    /**
     * 下载文件
     *
     * @param fileName
     * @param request
     * @param response
     */
    /**
     * 下载指南附件
     */
    @Override
    public void downloadFile(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            //String pathTest = "D:\\UPC\\offer"+"\\"+fileName;
            // fileName = URLEncoder.encode(fileName,"UTF-8");
            //转码，免得文件名中文乱码(有时候加上反而会乱码)
            System.out.println("路径："+path);
            System.out.println("路径："+path.trim());
            //从路径中获取文件名称，trim：去除字符串前后的空格
            File tempFile = new File(path.trim());
            String filename = tempFile.getName();

            //解决中文文件名乱码
            String userAgent = request.getHeader("User-Agent");
            //IE内核浏览器
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                filename = java.net.URLEncoder.encode(filename, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
            //设置文件下载头
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", filename));
            //设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("UTF-8");

            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            //创建缓冲区
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
                outputStream.flush();
            }
            //关闭流
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
