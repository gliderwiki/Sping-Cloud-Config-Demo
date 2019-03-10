package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Jasypt (Java Simplified Encryption) Test
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {

    private final String PASSWORD = "pass";

    @Test
    public void 암호화_복호화_테스트() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

        encryptor.setPassword("db");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setStringOutputType("base64");

        String encrypted = encryptor.encrypt(PASSWORD);
        String decrypted = encryptor.decrypt(encrypted);


        log.info("# encrypted = {}", encrypted);
        log.info("# decrypted = {}", decrypted);

        Assert.assertEquals(decrypted, PASSWORD);

    }
}
