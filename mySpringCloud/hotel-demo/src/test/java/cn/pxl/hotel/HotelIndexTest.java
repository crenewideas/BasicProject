package cn.pxl.hotel;

import cn.pxl.hotel.constants.HotelConstants;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HotelIndexTest {
    private RestHighLevelClient client;

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://10.211.55.5:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }

    //一：创建索引
    @Test
    void testCreateHotelIndex(){
        //创建Request对象
        CreateIndexRequest hotel = new CreateIndexRequest("hotel");
        //MAPPING_TEMPLATE是静态常量字符串，内容是创建索引库的DSL语句
        hotel.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);
        try {
            //发起请求
            CreateIndexResponse createIndexResponse = client.indices().create(hotel, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //二：删除索引
    @Test
    void testDeleteHotelIndex() throws IOException {
        // 1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        // 2.发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    //三：判断索引是否存在
    @Test
    void testExistsHotelIndex() throws IOException {
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 2.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        // 3.输出
        System.err.println(exists ? "索引库已经存在！" : "索引库不存在！");
    }

}