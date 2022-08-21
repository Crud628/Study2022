package fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.processing.Messager;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Keason
 * @Description: 发送日志消息
 * @date 2022/8/21 14:12
 */
public class EmitLog {

    /**
     * 交换机名字
      */
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 建立链接
        Connection connection = connectionFactory.newConnection();
        // 获得信道
        Channel channel = connection.createChannel();
        // 交换机   后面类型四个
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        // 消息内容
        String message = "info: hello World!";
        // 发送消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
        System.out.println("发送了消息" + message);
        // 关闭
        channel.close();
        connection.close();
    }
}
