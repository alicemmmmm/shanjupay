package com.shanjupay.test.rocketmq.message;

import com.shanjupay.test.rocketmq.model.OrderExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @ClassName ProducerSimpleTest
 * @Description TODO
 * @Author LiHongBin
 * @Date 2020\8\13 0013 16:04
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerSimpleTest {

    @Autowired
    ProducerSimple producerSimple;


    @Test
    public void testSendSyncMsg(){
        producerSimple.sendSyncMsg("my-topic","第二条同步消息");
    }

    @Test
    public void testSendASyncMsg() throws InterruptedException {
        producerSimple.sendASyncMsg("my-topic","第二条异步消息");
        Thread.sleep(5000);
    }

    @Test
    public void testSendMsgByJson() throws InterruptedException {
        OrderExt orderExt = new OrderExt();
        orderExt.setId("1111");
        orderExt.setMoney(893L);
        orderExt.setCreateTime(new Date());

        producerSimple.sendMsgByJson("my-topic-obj",orderExt);
    }

    @Test
    public void testSendMsgByJsonDelay(){
        OrderExt orderExt = new OrderExt();
        orderExt.setId("6666666");
        orderExt.setMoney(1111L);
        orderExt.setCreateTime(new Date());

        producerSimple.sendMsgByJsonDelay("my-topic-obj",orderExt);
    }
}
