package cn.pxl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

//开启随机端口测试，避免使用主启动类的端口测试。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootApplicationTest {

    @Test
    public void doTest(){
        String a = "aaa";
        Assert.isNull(a,"a is null");
    }

}
