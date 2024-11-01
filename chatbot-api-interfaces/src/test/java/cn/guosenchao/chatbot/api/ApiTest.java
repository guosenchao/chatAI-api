package cn.guosenchao.chatbot.api;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;

/**
 * 单元测试
 */

public class ApiTest {

    /**
     * 提问API
     * @throws IOException
     */
    @Test
    public void query_unanswered_questions() throws IOException {

        //1.创建httpclient连接对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //2.创建get请求,并添加请求头信息
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");
        get.addHeader("Cookie","_uab_collina=173044335914029527724816; abtest_env=product; zsxq_access_token=E0B42A74-F7B1-AFA0-1502-BF115FBDF9D4_13AC3F3146D37BB3; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22182882525888542%22%2C%22first_id%22%3A%22192e6881953f05-0aeb47fd30cfe4-26011951-1327104-192e688195438b%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZTY4ODE5NTNmMDUtMGFlYjQ3ZmQzMGNmZTQtMjYwMTE5NTEtMTMyNzEwNC0xOTJlNjg4MTk1NDM4YiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4Mjg4MjUyNTg4ODU0MiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22182882525888542%22%7D%2C%22%24device_id%22%3A%22192e68f839bddd-035f965e88ac7e2-26011951-1327104-192e68f839ced0%22%7D");
        get.addHeader("Context-Type","application/json");
        //3.发起get请求
        CloseableHttpResponse response = httpClient.execute(get);
        //4.对响应结果进行格式化处理
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 回答API
     * @throws IOException
     */
    @Test
    public void answered_questions() throws IOException {
        //1.创建httpclient连接对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //2.创建POST请求，并添加请求头，请求参数
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4848525111181518/comments");
        post.addHeader("Cookie","abtest_env=product; zsxqsessionid=a5098dbb92f8946520d3646e52bc2442; zsxq_access_token=E0B42A74-F7B1-AFA0-1502-BF115FBDF9D4_13AC3F3146D37BB3; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22182882525888542%22%2C%22first_id%22%3A%22192e6881953f05-0aeb47fd30cfe4-26011951-1327104-192e688195438b%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTkyZTY4ODE5NTNmMDUtMGFlYjQ3ZmQzMGNmZTQtMjYwMTE5NTEtMTMyNzEwNC0xOTJlNjg4MTk1NDM4YiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4Mjg4MjUyNTg4ODU0MiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22182882525888542%22%7D%2C%22%24device_id%22%3A%22192e68f839bddd-035f965e88ac7e2-26011951-1327104-192e68f839ced0%22%7D");
        post.addHeader("Context-Type","application/json");
        post.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity entity = new StringEntity(paramJson, ContentType.create("text/json", "utf-8"));
        post.setEntity(entity);

        //3.发起POST请求
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
