package com.parch.combine.rabbitmq.base.helper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RabbitMQHelper {

    private static final Map<String, RabbitMQHandler> MAP = new HashMap<>();

    public static Connection getConnection(String scope, RabbitMQConfig config) {
        return getHandler(scope).getConnection(config);
    }

    public static Channel getChannel(String scope, Connection connection, RabbitMQQueueConfig queue, boolean isSubscribe) {
        return getHandler(scope).getChannel(connection, queue, isSubscribe);
    }

    public static boolean publish(String scope, Channel channel, RabbitMQQueueConfig queue, Map<String, Object> message, boolean confirm) {
        return getHandler(scope).publish(channel, queue, message, confirm);
    }

    public static String subscribe(String scope, Channel channel, RabbitMQQueueConfig queue, int prefetchCount, Function<Map<String, Object>, Boolean> function) {
        return getHandler(scope).subscribe(channel, queue, prefetchCount, function);
    }

    public static boolean unSubscribe(String scope, Channel channel, String consumerTag){
        return getHandler(scope).unSubscribe(channel, consumerTag);
    }

    public static void destroy(String scope) {
        getHandler(scope).destroy();
    }

    private static RabbitMQHandler getHandler(String scope) {
        RabbitMQHandler handler = MAP.get(scope);
        if (handler != null) {
            return handler;
        }

        synchronized (MAP) {
            handler = MAP.get(scope);
            if (handler != null) {
                return handler;
            }

            handler = new RabbitMQHandler(scope);
            MAP.put(scope, handler);
            return handler;
        }
    }
}
