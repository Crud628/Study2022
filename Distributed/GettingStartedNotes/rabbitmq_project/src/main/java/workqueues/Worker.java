package workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Keason
 * @Description: 批量接收消息
 * @date 2022/8/21 12:39
 */
public class Worker {
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
        System.out.println("开始接收消息");
        channel.basicConsume(TASK_QUEUE_NAME, true, new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
              String message = new String(body, "UTF-8");
               System.out.println("接收到消息" + message);
               try {
                   doWork(message);
               } finally {
                   System.out.println("消息处理完成");
               }

           }
        });
        // channel.close();
        // connection.close();
    }

    private static void doWork(String task) {
        char[] chars = task.toCharArray();
        for (char ch: chars) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
