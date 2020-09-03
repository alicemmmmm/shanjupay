package com.shanjupay.test.rocketmq.message;

import com.alibaba.fastjson.JSON;
import com.shanjupay.test.rocketmq.model.OrderExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConsumerSimpleObj
 * @Description TODO
 * @Author LiHongBin
 * @Date 2020\8\13 0013 21:45
 * @Version 1.0
 **/
@Component
@RocketMQMessageListener(topic = "my-topic-obj",consumerGroup = "dome-consumer-group-obj")
public class ConsumerSimpleObj implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
//        String body = messageExt.getBody().toString();
        byte[] body = messageExt.getBody();
        String jsonBody = new String(body);
        OrderExt orderExt = JSON.parseObject(jsonBody, OrderExt.class);
        System.out.println(orderExt);

        //拿到重试次数
        int reconsumeTimes = messageExt.getReconsumeTimes();
        if (reconsumeTimes > 2){
            //不再重试,写入数据库,由单独程序或人工出路
            System.out.println("第"+reconsumeTimes+"处理消息,不再重试!");
            return;
        }

        if (1 == 1){
            throw new RuntimeException("第"+reconsumeTimes+"处理消息失败!!!");
        }
    }
}
