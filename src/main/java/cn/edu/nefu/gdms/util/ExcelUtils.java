package cn.edu.nefu.gdms.util;


import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.exception.ServiceException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class ExcelUtils<T> {

    public List<T> obatainList(File file, Class<T> type, Map<String, String> map) {
        List<T> list = new ArrayList<T>();

        HSSFCell[][] cellArr = getCellsFromFile(file);
        //初始化第一列的值,预处理
        Map<String, Integer> paramMap = getParamMap(cellArr, map);

        for (int i = 1; i < cellArr.length; i++) {
            try {
                T target = (T) type.newInstance();
                Field[] fields = type.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
                    int j = paramMap.get(field.getName());
                    Method getMethod = pd.getWriteMethod();
                    getMethod.invoke(target, afterTransValue(cellArr[i][j], field));
                }

                list.add(target);
            } catch (Exception e) {
                throw new ServiceException(ErrorCodeEnum.RELECT_ERROR);
            }
        }

        return list;
    }

    public File obtainFile(List<T> list, Class<T> type, String fileName, EqualMap<String, String> map) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCell[][] cellArr = initExcel(wb, list.size(), map.size());

        int index = 0;
        String[] str = (String[]) map.keySet().toArray();
        for (int i = 0; i < map.size(); i++) {
            cellArr[0][i + 1].setCellValue(str[i]);
            for (T t : list) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(map.getFromKey(str[i]), type);
                    Method getMethod = pd.getReadMethod();//获得get方法
                    Object o = getMethod.invoke(t);//执行get方法返回一个Object
                    Field field = type.getDeclaredField("map.getFromKey(str[i])");
                    setTranseValue(cellArr[index++][i + 1], field, o);
                } catch (Exception e) {
                    throw new ServiceException(ErrorCodeEnum.RELECT_ERROR);
                }
            }
        }

        try {
            FileOutputStream os = new FileOutputStream(fileName);
            wb.write(os);
            os.close();
        } catch (Exception e) {
            throw new ServiceException(ErrorCodeEnum.SERVER_ERROR);
        }
        File f = new File(fileName);
        return f;

    }

    private void setTranseValue(HSSFCell cell, Field field, Object object) {
        if (field.getGenericType().toString().equals("long") || field.getGenericType().toString().equals("int")) {
            cell.setCellValue(Double.valueOf(object.toString()));
        } else if (field.getGenericType().equals("class java.lang.String")) {
            cell.setCellValue(object.toString());
        }
    }


    private Object afterTransValue(HSSFCell cell, Field field) {
        Object rs = null;
        if (field.getGenericType().toString().equals("class java.lang.String")) {
            rs = cell.getStringCellValue();
        } else if (field.getGenericType().toString().equals("long") || field.getGenericType().toString().equals("int")) {
            rs = cell.getNumericCellValue();
        }
        return rs;
    }

    private HSSFCell[][] initExcel(HSSFWorkbook wb, int height, int width) {

        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = wb.createSheet("sheet1");

        // 设置excel每列宽度
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 3500);

        // 创建字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("Verdana");
        font.setBoldweight((short) 100);
        font.setFontHeight((short) 300);
        font.setColor(HSSFColor.BLUE.index);

        // 创建单元格样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // 设置边框
        style.setBottomBorderColor(HSSFColor.RED.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        style.setFont(font);// 设置字体
        HSSFCell[][] cellArr = new HSSFCell[height + 1][width + 1];


        //创建listSize+1行，将数组行与每个cell绑定
        for (int i = 0; i < height + 1; i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < width + 1; j++) {
                HSSFCell cell = row.createCell(j);
                cellArr[i][j] = cell;
            }
        }

        return cellArr;
    }


    private HSSFCell[][] getCellsFromFile(File file) {
        FileInputStream input = null;
        HSSFWorkbook wb = null;
        try {
            input = new FileInputStream(file);
            //得到Excel工作簿对象
            wb = new HSSFWorkbook(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(0);
        //总行数
        int height = sheet.getLastRowNum();
        //得到Excel工作表的行
        HSSFRow row = sheet.getRow(0);
        //总列数
        int width = row.getLastCellNum();
        //5.得到Excel工作表指定行的单元格
        HSSFCell cell = row.getCell((short) 1);

        HSSFCell[][] cellArr = new HSSFCell[height + 1][width];
        for (int i = 0; i < height + 1; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < width; j++) {
                cell = row.getCell(j);
                cellArr[i][j] = cell;
            }
        }
        return cellArr;
    }

    private Map<String, Integer> getParamMap(HSSFCell[][] cellArr, Map<String, String> map) {
        Map<String, Integer> paramMap = new HashMap<String, Integer>();
        for (int i = 0; i < cellArr[0].length; i++) {
            String str = cellArr[0][i].getStringCellValue();
            paramMap.put(map.get(str), i);
        }
        return paramMap;
    }


}
