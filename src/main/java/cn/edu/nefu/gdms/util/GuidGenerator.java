package cn.edu.nefu.gdms.util;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

import java.util.UUID;

/**
 * 随机数生成处理工具
 *
 * @author
 */
public abstract class GuidGenerator {

    private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);

    /**
     * private constructor
     */
    private GuidGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String generateClientSecret() {
        return defaultClientSecretGenerator.generate();
    }
}