package workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Keason
 * @Description: 批量发送消息
 * @date 2022/8/21 12:32
 */
public class NewTask {
    private final static String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置RabbitMQ地址
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        // 建立链接
        Connection connection = connectionFactory.newConnection();
        // 获得信道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            String message;
            message = i + "...";
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes());
            System.out.println("发送了消息" + message);
        }
        channel.close();
        connection.close();
    }
}
