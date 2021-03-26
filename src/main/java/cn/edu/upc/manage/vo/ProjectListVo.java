package cn.edu.upc.manage.vo;

import cn.edu.upc.manage.model.ProjectList;

import java.util.List;

/**
 * @author dzh
 */
public class ProjectListVo extends ProjectList {
    private List<String> fileList;

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
