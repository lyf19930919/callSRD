package mainCall;

import com.alibaba.fastjson.JSONObject;
import entity.TransportOrder;
import enumUtil.RequestHeaders;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.StringMatch;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @description:关于订单的相关操作
 * @ClassName:OrderOperations
 * @Description:
 * @Date: Create in 13:09 2019/5/16
 */
public class OrderOperations {
    public static final Logger logger = LoggerFactory.getLogger(OrderOperations.class);

    public static void createOrderByPost(String orderName,TransportOrder transportOrder,String roborouteIpAddres) {
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
        HttpPost post = new HttpPost(StringMatch.replaceUrl(roborouteIpAddres)+orderName);
        // 构造消息头
        post.setHeader("Content-type",RequestHeaders.CreateTransportOrder.getContentType());
        // 构建消息实体
        try {
            StringEntity entity = new StringEntity(JSONObject.toJSONString(transportOrder), "UTF-8");
            entity.setContentEncoding("Content-type");
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
              logger.info("response status is 200,this order has been exited in Roboroute");
            }else{
                logger.info("requestBody is false, please check this order requestBody");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





