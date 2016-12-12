package cn.edu.nefu.gdms.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class ExcelUtils<T> {


    public List<T> obatainList(File f) {
        List<T> list = new ArrayList<T>();

        return list;
    }


    private HSSFWorkbook getWorkBookFromFile(File file) {
        FileInputStream input = null;
        HSSFWorkbook wb = null;
        try {
            input = new FileInputStream(file);
            //得到Excel工作簿对象
            wb = new HSSFWorkbook(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }


}
