package cn.pxl;

import static org.junit.Assert.assertTrue;

import cn.pxl.json.JsonTest;
import cn.pxl.json.common.User;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        User user = JsonTest.objectToJson();
        String jsonString = JSONObject.toJSONString(user).replace("n","a").substring(1);
        System.out.println(jsonString);
        User user1 = JSONObject.parseObject(jsonString, User.class);
        System.out.println(user1);
    }
}
