package com.wyl.springbootjwt;

import com.wyl.springbootjwt.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;


@SpringBootTest
class SpringbootJwtApplicationTests {

    @Test
    void contextLoads() {
        String gensalt = BCrypt.hashpw("123456",BCrypt.gensalt());
        System.out.println(gensalt);
        String s = MD5Util.string2MD5("123456");
        System.out.println(s);
    }

}
