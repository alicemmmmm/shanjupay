package com.shanjupay.merchant.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付宝接口对接测试类
 * @author Administrator
 * @version 1.0
 **/

@Slf4j
@Controller
//@RestController//请求方法响应统一json格式
public class PayTestController {

    //应用id
    String APP_ID = "2016102400750547";
    //应用私钥
    String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfrCZERVnGSLylmdAODEbFMYL/NkY85tZKOaXi/OcOkUg5/3359TZwPpnjPERmGzEqMM6F61yHKGzYTRdJvy+F+xAV9s1+6oJBLqdhrfGhHBY/tJYCzSGNNWTiQJ3u+BNpTZ9jhoz4vPTnZdxajSJL3+uKRwmgRH1H/YbsmR+vtIXINOufiJYDPPAHnSi/61/TRw3F3l1NHdftlOALr8NRoopSRwrIQNr7MhDl8dpXIrptzEzt0vfxeIyEIVtQ1EPTThTjBb66yquPOZF9HkSfeXJh9oKnX74qKa24QpCcg605omf7SdSAM0qRl5ZQx/9vLDpaQWFqM4YBu0k7kul/AgMBAAECggEAWmMOKnmDr24/dBMVJs6mRTyY5jXoHtOhnNTzZgI6HhH56/MIcjXwgiu921ejdJWSCEsmrcKlV1F4BB3nv/R9R3/vBC/49g50e8eYMwBPlx8aLSfh/2X1x0Thff6MKNm/y02Im8NwC+KTVGZiDdR1BLSWVmmONm5u6RDIjyjDOie1mbwP2OVmif4AsiwsMRfcMrXwrNyulbmjJl2zTr7qFVsvpc5YyFQngeTT4674k1C6rMJoZqnKXT/WVsWF8W28U/t1UkX6Vn2mpWSUFb6aywQyu7CPaPyDTqmmQQOD3DgfwTScMwayAvs5nyIyEAcMQVcJokgaDLlgk+FhIkEYyQKBgQDg/2E1uuW3ozxGH/zIzjWSZEIrCT4VgJZPnmt7k3svBMnkrrPuQmmjltWGnBDsvF+iORSP8CH5LTiWae/kFEACv7nVaHZqo7HFghXeceKdCyUl6B7xP8YtdzDG0mQJ3Pq6n0v1tm0xrYOHCTDDnD/JlzNaUGYnq0qhm4vExs4VfQKBgQC1rHmwp+yeIs48wI6W/L5e7seyWik5TFvcPxBfqCe2DGasE7+dg+i0CN20VFyDdbKYe2YFnic7+vLIV1iUW11AmjAQp3PBxtFDmbpP9UAUu8IwsgtT3psL+z95NeqG8Xd9XKNgcpeHF6Cc9rTqBvbSmwLzMuizKtf9qrbep437qwKBgC4NV3JN7NLngGDCD1Vyj5iFGN4CKEjyZtGpFUrX5vC6xDAgagesZD7tkorYysYJ0HE9ya2kcu+zh8+Ro15FnsxxPbImjYZKNwq8E4nSOZX3QAVAO4UZjpCjwsIPMhaquk8UZ9HFIxh18WPkR6ziAFRctFAaAK7B6qalVbhTEL9pAoGBAJExc9wMiN3lYUBKgGsJObgzS6QtzI5/iVbfi913Ta2AAbp62gQwPRVNlKqfVYS21vJohKPuvtp/9/LHZALFdiTAVbOpsKjolaxaOv3WOs2XNV6nnMf9u5p6xULBJVuNtfqZw/S8AIA2EOYBgZsbz72Hp3boc/NlRVP1UMtny+CbAoGBAJJfRLshuqa9kkYmBIloZR+LnRroTLes0aWZ6oMLjrmNqMF1E8ab1F3fkxnIR9fh3eN38BzExNgSHh0ETUjoCA1q8TqWmLPYYhjJ2wNPA2Vi3PTH3VFszRaEY1NpqzBe0r297cU13GzSAB8jCkw+8qG6QBNnJoUrNmGKW+GACcaX";
   //支付宝公钥
    String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4uCVPCJTkuuCeUTVZaZXTLai+/o899MwxLJNWaCj9QYVD4a1KpQrvnp4Rwn5UR8KOFlyuLleh25a+xFNnAxaSUbqHGeX0/IlGgm4nVlb+GZf4Nmeo2SbEZxJ9mS45lKW3adHs2B/UWvyLUH+TC092iOAzDlr1WNzMuzwxSR33xP2UsZKZLZI8ijc/QKj/8j5ceN4SBeXyAJtsu0ZIpEYb+sHsb/m001CV1BFum90va6Mln4wz/TUN8czUtdtqjZVpPXYgHN7sFw4wjUKcGkzM9FyKm0sbarN2Bszr89l0IcHrkMnHznz+Cg/bGj4jDx69lQ3tcmbrTP5dvlfzuIDgQIDAQAB";
    String CHARSET = "utf-8";
    //支付宝接口的网关地址，正式"https://openapi.alipay.com/gateway.do"
    String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    //签名算法类型
    String sign_type = "RSA2";

    @RequestMapping("/test")
    public void test(){
        System.out.println("test");
    }

    @GetMapping("/alipaytest")
    public void alipaytest(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {
        //构造sdk的客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, sign_type); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
//        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
//        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"20150420010101017\"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            //请求支付宝下单接口,发起http请求
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
