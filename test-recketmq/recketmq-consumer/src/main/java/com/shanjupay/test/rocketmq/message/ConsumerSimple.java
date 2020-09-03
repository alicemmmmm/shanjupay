package com.shanjupay.test.rocketmq.message;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConsumerSimple
 * @Description TODO
 * @Author LiHongBin
 * @Date 2020\8\13 0013 21:03
 * @Version 1.0
 **/
@Component
@RocketMQMessageListener(topic = "my-topic",consumerGroup = "dome-consumer-group")
public class ConsumerSimple implements RocketMQListener<String>{

    //得到消息
    @Override
    public void onMessage(String msg) {
        //次方法被调用表示接受到消息,msg形参就是消息
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
    }
}
