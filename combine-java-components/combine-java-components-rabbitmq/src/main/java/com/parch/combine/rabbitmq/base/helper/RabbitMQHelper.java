package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class RabbitMQHelper {

    private static final Map<String, Channel> PRODUCER_MAP = new ConcurrentHashMap<>();
    private static final Map<String, Connection> COON_MAP = new ConcurrentHashMap<>();

    public static Connection getConnection(RabbitMQConfig config) {
        String key = config.key();
        Connection connection = COON_MAP.get(key);
        if (connection != null) {
            return connection;
        }

        synchronized (COON_MAP) {
            connection = COON_MAP.get(key);
            if (connection != null) {
                return connection;
            }

            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setVirtualHost(config.virtualHost());
                factory.setUsername(config.username());
                factory.setPassword(config.password());
                factory.setAutomaticRecoveryEnabled(config.autoMaticRecoveryEnabled());
                factory.setNetworkRecoveryInterval(config.networkRecoveryInterval());

                RabbitMQConfig.HostConfig[] hostConfigs = config.host();
                Address[] addresses = new Address[hostConfigs.length];
                for (int i = 0; i < hostConfigs.length; i++) {
                    addresses[i] = new Address(hostConfigs[i].host(), hostConfigs[i].port());
                }
                connection = factory.newConnection(addresses, config.applicationName());

                COON_MAP.put(key, connection);

                return connection;
            } catch (IOException | TimeoutException e) {
                PrintErrorHelper.print(RabbitMQErrorEnum.CREATE_CONN_ERROR, e);
            }
        }

        return null;
    }

    public static Channel getChannel(Connection connection, RabbitMQQueueConfig queue) {
        String key = queue.key();
        Channel channel = PRODUCER_MAP.get(key);
        if (channel != null && channel.isOpen()) {
            return channel;
        }

        synchronized (PRODUCER_MAP) {
            channel = PRODUCER_MAP.get(key);
            if (channel != null && channel.isOpen()) {
                return channel;
            }

            try {
                String queueName = queue.queueName();
                String routeKey = queue.routeKey();

                channel = connection.createChannel();
                channel.queueDeclare(queueName, queue.durable(), false, false, getPropertiesByQueue(queue));
                channel.queueBind(queueName, queue.exchange(), routeKey == null ? queueName : routeKey);
                PRODUCER_MAP.put(key, channel);

                return channel;
            } catch (Exception e) {
                PrintErrorHelper.print(RabbitMQErrorEnum.CREATE_CHANNEL_ERROR, e);
            }
        }

        return null;
    }

    /**
     * 发送消息
     */
    public static boolean publish(Channel channel, RabbitMQQueueConfig queue, Map<String, Object> message, boolean confirm) {
        try {
            String queueName = queue.queueName();
            String routeKey = queue.routeKey();

            channel.basicPublish(queue.exchange(), routeKey == null ? queueName : routeKey, queue.durable(),
                    null, JsonUtil.serialize(message).getBytes(StandardCharsets.UTF_8));
            if (confirm) {
                return channel.waitForConfirms(15);
            } else {
                return true;
            }
        } catch (Exception e) {
            PrintErrorHelper.print(RabbitMQErrorEnum.PUBLISH_ERROR, e);
        }

        return false;
    }

    /**
     * 订阅消息
     */
    @SuppressWarnings("unchecked")
    public static String subscribe(Channel channel, RabbitMQQueueConfig queue, int prefetchCount, Function<Map<String, Object>, Boolean> function) {
        try {
            boolean autoAck = queue.autoAck();
            channel.basicQos(prefetchCount == 0 ? 1 : prefetchCount);
            return channel.basicConsume(queue.queueName(), autoAck, (s, delivery) -> {
                Map<String, Object> data = JsonUtil.deserialize(new String(delivery.getBody(),StandardCharsets.UTF_8), HashMap.class);
                boolean result = function.apply(data);
                if (!autoAck) {
                    if (result) {
                        // 如果非自动确认且消息消费返回结果为false重新入队列
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    } else {
                        // 如果为非自动确认且消费成功做正常ACK处理
                        channel.basicReject(delivery.getEnvelope().getDeliveryTag(), true);
                    }
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {}
            });

        } catch (Exception e) {
            PrintErrorHelper.print(RabbitMQErrorEnum.SUBSCRIBE_ERROR, e);
        }

        return null;
    }

    /**
     * 取消订阅
     * @param channel 连接通道
     * @param consumerTag 消费者标识
     */
    public static boolean unSubscribe(Channel channel,String consumerTag){
        try{
            channel.basicCancel(consumerTag);
            return true;
        }catch (Exception e){
            PrintErrorHelper.print(RabbitMQErrorEnum.CANCEL_ERROR, e);
        }

        return false;
    }

    public synchronized static void destroy() {
        PRODUCER_MAP.forEach((s, channel) -> {
            try {
                if (channel.isOpen()) {
                    channel.close();
                }
            } catch (IOException | TimeoutException e) {
                PrintErrorHelper.print(RabbitMQErrorEnum.DESTROY_CHANNEL_ERROR, e);
            }
        });

        COON_MAP.forEach((s, connection) -> {
            try {
                if (connection.isOpen()) {
                    connection.close();
                }
            } catch (IOException e) {
                PrintErrorHelper.print(RabbitMQErrorEnum.DESTROY_CONN_ERROR, e);
            }
        });
    }

    /**
     * 构建队列附属参数
     */
    private static Map<String, Object> getPropertiesByQueue(RabbitMQQueueConfig queue) {
        Map<String, Object> args = new HashMap<String, Object>();
        if (queue.expireTime() != 0) {
            args.put("x-message-ttl", queue.expireTime());
        }
        if (queue.deadQueueName() != null) {
            args.put("x-dead-letter-exchange", queue.exchange());
            args.put("x-dead-letter-routing-key", queue.deadQueueName());
        }

        return args;
    }
}
