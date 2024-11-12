package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

public interface ExchangeConfig {
    
    /**
     * 交换器的类型
     */
    @Field(key = "type", name = "类型", type = FieldTypeEnum.SELECT, defaultValue = "direct")
    @FieldSelect(enumClass = ExchangeTypeEnum.class)
    String type();

    /**
     * 交换器是否应该被持久化
     * 如果设置为true，则交换器在RabbitMQ服务器重启后依然存在
     */
    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean durable();

    /**
     * 则当没有其他交换器或队列绑定到该交换器时，服务器是否会自动删除它
     */
    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean autoDelete();

    /**
     * 否为内部交换器
     * 如果是内部交换器，客户端程序无法直接发送消息到这个交换器，只能通过其他交换器路由到这个交换器
     */
    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean internal();

    /**
     * 其他配置参数配置JSON
     * 主要包含：
     * alternate-exchange：备用交换器的名称。如果消息无法路由到任何队列，它会将消息发送到这个备用交换器
     * x-message-ttl：消息的存活时间（以毫秒为单位）。如果消息在队列中等待的时间超过了这个值，它将被丢弃或发送到死信交换器
     * x-expires：整个队列或交换器的存活时间（以毫秒为单位）。如果队列或交换器在这段时间内没有被使用，它将被自动删除
     * x-dead-letter-exchange：死信交换器的名称。如果消息在队列中无法被消费（例如，因为消费者无法处理该消息），它会将消息发送到这个死信交换器
     * x-max-length：队列中消息的最大数量。如果队列中的消息数量超过了这个值，新的消息将被拒绝或发送到死信交换器
     * x-max-length-bytes：队列中消息体的最大字节数。如果队列中的消息体总大小超过了这个值，新的消息将被拒绝或发送到死信交换器
     * x-max-priority：队列的最大优先级级别。这允许你设置不同的消息优先级
     * x-queue-mode：队列的模式，例如“lazy”模式可以在需要时才创建队列
     * x-arguments：其他扩展参数，可以用于插件或特定用途的配置。
     */
    @Field(key = "arguments", name = "其他参数", type = FieldTypeEnum.MAP)
    Map<String, Object> arguments();

}

