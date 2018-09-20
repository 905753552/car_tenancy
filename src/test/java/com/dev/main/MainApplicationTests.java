package com.dev.main;

import com.dev.main.common.util.CryptographyUtil;
import com.dev.main.common.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args){
        System.out.println(CryptographyUtil.MD5Hash("123456", "123456"));;
    }

}
