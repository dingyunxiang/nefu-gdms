package cn.edu.nefu.gdms.util;


import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.exception.ServiceException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class FileUtils {

    public static String getTeacherFilePath(int years, long tutorId) {
        return years + "/teacher/" + tutorId + "/";
    }

    public static String getStudentFileParh(int years, long studentId) {
        return years + "/student/" + studentId + "/";
    }

    // 保存文件
    public static void saveFile(String newsRootPath, String filename,
                                File picFile) {
        try {
            File newsFileRoot = new File(newsRootPath);
            if (!newsFileRoot.exists()) {
                newsFileRoot.mkdirs();
            }else{
                throw new ServiceException(ErrorCodeEnum.FILE_REPEAT);
            }

            FileOutputStream fos = new FileOutputStream(newsRootPath + filename);
            FileInputStream fis = new FileInputStream(picFile);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // 删除文件
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    // 删除文件与目录
    public static boolean deleteFolder(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    // 删除目录
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

}
