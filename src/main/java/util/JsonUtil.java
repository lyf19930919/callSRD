package util;

import com.alibaba.fastjson.JSONObject;
import entity.TransportOrder;
import mainCall.OrderOperations;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.CoreConnectionPNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static final Logger logger = LoggerFactory.getLogger(OrderOperations.class);


    //默认时间是上次在设置框中的设置时间和产线的名称
    public static TransportOrder updateOrderBody(String waitTime, String actionName) {
        String text = null;
        TransportOrder requestHeaders = null;
        List<TransportOrder.Destination> destinationList = null;
        try {
//            text = FileUtils.readFileToString(orderBodyPath, StandardCharsets.UTF_8.name());
            text =IOUtils.toString((JsonUtil.class.getResourceAsStream("/orderBody.json")),StandardCharsets.UTF_8.name());
            logger.info("IOutil get text is: "+text);
            destinationList = new ArrayList<TransportOrder.Destination>();
            logger.info(text);
            requestHeaders = JSONObject.parseObject(text, TransportOrder.class);

            StringEntity entity = new StringEntity(JSONObject.toJSONString(requestHeaders), "UTF-8");
        } catch (IOException e) {
            logger.error("Exception message is：" + e.getMessage());
        }
        for (TransportOrder.Destination destination : requestHeaders.destinations) {
//      修改起始时间
            for (TransportOrder.Destination.Property property : destination.properties) {
                if (!property.getKey().isEmpty() && "duration".equals(property.getKey()) && waitTime != null) {
                    property.setValue(waitTime);
                }
            }
            //      修改工作站位置
            if (!destination.getOperation().isEmpty() && "WaitKey".equals(destination.getOperation()) && actionName != null) {
                destination.setLocationName(actionName);
            }
            for (TransportOrder.Destination.Property property2 : destination.properties) {
                logger.info("transportOrder(duration) property is: " + property2.getValue());
            }
            destinationList.add(destination);
        }
        requestHeaders.setDestinations(destinationList);

        return requestHeaders;
    }


}
