package enumUtil;

import lombok.Data;

/**
 * @description:
 * @ClassName:RequestHeaders
 * @Description:
 * @Date: Create in 12:25 2019/5/16
 */

public enum RequestHeaders {
//    获取所有的业务订单
    AllTransportOrder("http://localhost:55200/v1/transportOrders","","Get"),
//    获取指定名称的业务订单
    TransportOrderByName("localhost:55200/v1","",""),
//    创建业务订单
    CreateTransportOrder("http://localhost:55200/v1/transportOrders/","application/json; charset=utf-8","Post"),
//    撤销业务订单
    CancelTransportOrder("localhost:55200/v1","",""),

//    获取所有机器人的业务状态
    ALLvehicles("localhost:55200/v1","",""),
//    以机器人的名称获取该机器人正在执行的订单
    OrdByVehicleName("localhost:55200/v1","",""),
//    以机器人名撤销该机器人还未执行的订单
    cancelOrdbyVehicleName("localhost:55200/v1","",""),
//    将指定机器人置为在线
    vehicleOnline("localhost:55200/v1","","");
    String Url;
    String contentType;
    String requestType;

    RequestHeaders(String url, String contentType, String requestType) {
        Url = url;
        this.contentType = contentType;
        this.requestType = requestType;
    }

    public String getUrl() {
        return Url;
    }

    public String getContentType() {
        return contentType;
    }

    public String getrequestType() {
        return requestType;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setrequestType(String requestType) {
        this.requestType = requestType;
    }

}
