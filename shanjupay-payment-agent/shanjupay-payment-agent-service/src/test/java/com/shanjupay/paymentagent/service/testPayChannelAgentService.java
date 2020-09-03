package com.shanjupay.paymentagent.service;

import com.shanjupay.paymentagent.api.PayChannelAgentService;
import com.shanjupay.paymentagent.api.conf.AliConfigParam;
import com.shanjupay.paymentagent.api.dto.PaymentResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName testPayChannelAgentService
 * @Description TODO
 * @Author LiHongBin
 * @Date 2020\8\14 0014 11:06
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class testPayChannelAgentService {

    @Autowired
    PayChannelAgentService payChannelAgentService;


    @Test
    public void testQueryPayOrderByAli(){
        //应用id
        String APP_ID = "2016102400750547";
        //应用私钥
        String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfrCZERVnGSLylmdAODEbFMYL/NkY85tZKOaXi/OcOkUg5/3359TZwPpnjPERmGzEqMM6F61yHKGzYTRdJvy+F+xAV9s1+6oJBLqdhrfGhHBY/tJYCzSGNNWTiQJ3u+BNpTZ9jhoz4vPTnZdxajSJL3+uKRwmgRH1H/YbsmR+vtIXINOufiJYDPPAHnSi/61/TRw3F3l1NHdftlOALr8NRoopSRwrIQNr7MhDl8dpXIrptzEzt0vfxeIyEIVtQ1EPTThTjBb66yquPOZF9HkSfeXJh9oKnX74qKa24QpCcg605omf7SdSAM0qRl5ZQx/9vLDpaQWFqM4YBu0k7kul/AgMBAAECggEAWmMOKnmDr24/dBMVJs6mRTyY5jXoHtOhnNTzZgI6HhH56/MIcjXwgiu921ejdJWSCEsmrcKlV1F4BB3nv/R9R3/vBC/49g50e8eYMwBPlx8aLSfh/2X1x0Thff6MKNm/y02Im8NwC+KTVGZiDdR1BLSWVmmONm5u6RDIjyjDOie1mbwP2OVmif4AsiwsMRfcMrXwrNyulbmjJl2zTr7qFVsvpc5YyFQngeTT4674k1C6rMJoZqnKXT/WVsWF8W28U/t1UkX6Vn2mpWSUFb6aywQyu7CPaPyDTqmmQQOD3DgfwTScMwayAvs5nyIyEAcMQVcJokgaDLlgk+FhIkEYyQKBgQDg/2E1uuW3ozxGH/zIzjWSZEIrCT4VgJZPnmt7k3svBMnkrrPuQmmjltWGnBDsvF+iORSP8CH5LTiWae/kFEACv7nVaHZqo7HFghXeceKdCyUl6B7xP8YtdzDG0mQJ3Pq6n0v1tm0xrYOHCTDDnD/JlzNaUGYnq0qhm4vExs4VfQKBgQC1rHmwp+yeIs48wI6W/L5e7seyWik5TFvcPxBfqCe2DGasE7+dg+i0CN20VFyDdbKYe2YFnic7+vLIV1iUW11AmjAQp3PBxtFDmbpP9UAUu8IwsgtT3psL+z95NeqG8Xd9XKNgcpeHF6Cc9rTqBvbSmwLzMuizKtf9qrbep437qwKBgC4NV3JN7NLngGDCD1Vyj5iFGN4CKEjyZtGpFUrX5vC6xDAgagesZD7tkorYysYJ0HE9ya2kcu+zh8+Ro15FnsxxPbImjYZKNwq8E4nSOZX3QAVAO4UZjpCjwsIPMhaquk8UZ9HFIxh18WPkR6ziAFRctFAaAK7B6qalVbhTEL9pAoGBAJExc9wMiN3lYUBKgGsJObgzS6QtzI5/iVbfi913Ta2AAbp62gQwPRVNlKqfVYS21vJohKPuvtp/9/LHZALFdiTAVbOpsKjolaxaOv3WOs2XNV6nnMf9u5p6xULBJVuNtfqZw/S8AIA2EOYBgZsbz72Hp3boc/NlRVP1UMtny+CbAoGBAJJfRLshuqa9kkYmBIloZR+LnRroTLes0aWZ6oMLjrmNqMF1E8ab1F3fkxnIR9fh3eN38BzExNgSHh0ETUjoCA1q8TqWmLPYYhjJ2wNPA2Vi3PTH3VFszRaEY1NpqzBe0r297cU13GzSAB8jCkw+8qG6QBNnJoUrNmGKW+GACcaX";
        //支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4uCVPCJTkuuCeUTVZaZXTLai+/o899MwxLJNWaCj9QYVD4a1KpQrvnp4Rwn5UR8KOFlyuLleh25a+xFNnAxaSUbqHGeX0/IlGgm4nVlb+GZf4Nmeo2SbEZxJ9mS45lKW3adHs2B/UWvyLUH+TC092iOAzDlr1WNzMuzwxSR33xP2UsZKZLZI8ijc/QKj/8j5ceN4SBeXyAJtsu0ZIpEYb+sHsb/m001CV1BFum90va6Mln4wz/TUN8czUtdtqjZVpPXYgHN7sFw4wjUKcGkzM9FyKm0sbarN2Bszr89l0IcHrkMnHznz+Cg/bGj4jDx69lQ3tcmbrTP5dvlfzuIDgQIDAQAB";
        String CHARSET = "utf-8";
        String serverUrl = "https://openapi.alipaydev.com/gateway.do";
        AliConfigParam aliConfigParam = new AliConfigParam();
        aliConfigParam.setUrl(serverUrl);
        aliConfigParam.setCharest(CHARSET);
        aliConfigParam.setAlipayPublicKey(ALIPAY_PUBLIC_KEY);
        aliConfigParam.setRsaPrivateKey(APP_PRIVATE_KEY);
        aliConfigParam.setAppId(APP_ID);
        aliConfigParam.setFormat("json");
        aliConfigParam.setSigntype("RSA2");

        //AliConfigParam aliConfigParam,String outTradeNo
        PaymentResponseDTO paymentResponseDTO = payChannelAgentService.queryPayOrderByAli(aliConfigParam, "SJ1292087012910809088");
        System.out.println(paymentResponseDTO);
    }
}
