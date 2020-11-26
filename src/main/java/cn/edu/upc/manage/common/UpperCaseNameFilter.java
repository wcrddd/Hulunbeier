package cn.edu.upc.manage.common;

import com.alibaba.fastjson.serializer.NameFilter;

public class UpperCaseNameFilter implements NameFilter {
    @Override
    public String process(Object object, String name, Object value) {
        if (name.equals("dbid") || name.equals("bizid") || name.equals("bindLiziForm")) {
            return name;
        }
        return name.toUpperCase();
    }
}
