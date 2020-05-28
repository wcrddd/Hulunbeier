package cn.edu.upc.gsl.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作文件
 * @author gsl
 * @date 2020/5/20
 */
public interface FileService2 {
    /**
     * 下载文件
     * @param path 文件路径，如 /guide/appendix.doc
     * @param request
     * @param response
     */
    void downloadFile(String path,HttpServletRequest request, HttpServletResponse response);
}
