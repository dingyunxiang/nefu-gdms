package cn.edu.nefu.gdms.util;

import ch.qos.logback.core.util.FileUtil;

import java.io.File;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class FileUtils {

    public static String getTopicFilePath(int years, long tutorId) {
        return years + "/teacher/" + tutorId + "/";
    }

    public static void save(File file, String filePath) {

    }

}
