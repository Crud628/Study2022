package direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Keason
 * @Description:
 * @date 2022/8/21 19:16
 */
public class EmitLogDirect {
    /**
     * 交换机名字
     */
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 建立链接
        Connection connection = connectionFactory.newConnection();
        // 获得信道
        Channel channel = connection.createChannel();
        // 交换机   后面类型四个
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        // 消息内容
        String message = "info: hello World!";
        // 发送消息  添加routingKey
        channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes("UTF-8"));
        System.out.println("发送了消息，等级为info，消息内容" + message);
        channel.basicPublish(EXCHANGE_NAME, "warning", null, message.getBytes("UTF-8"));
        System.out.println("发送了消息，等级为warning，消息内容" + message);
        channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("UTF-8"));
        System.out.println("发送了消息，等级为error，消息内容" + message);
        // 关闭
        channel.close();
        connection.close();
    }
}
