package com.parch.combine.components.access.redis.command;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

public enum RedisCommandTypeEnum implements IOptionSetting {

    SET("设置值", "STRING", 4, "参数格式：{KEY} {NX/XX/-} {有效期毫秒数/-} {值}", true),
    GET("获取值", "STRING", 1, "参数格式：{KEY}", true),
    APPEND("值末尾追加字符串", "STRING", 2, "参数格式：{KEY} {追加值}", true),
    INCR("对整数值加一", "STRING", 1, "参数格式：{KEY}", true),
    DECR("对整数值减一", "STRING", 1, "参数格式：{KEY}", true),

    EXPIRE("设置有效期", "KEY", 2, "参数格式：{KEY} {有效期毫秒数}", true),
    DEL("删除KEY", "KEY", 1, "参数格式：{KEY}", true),

    LSET("根据集合索引位置设置值", "LIST", 3, "参数格式：{KEY} {索引} {值}", true),
    LPUSH("左侧添加集合值", "LIST", 2, "参数格式：{KEY} {值}...", true),
    RPUSH("右侧添加集合值", "LIST", 2, "参数格式：{KEY} {值}...", true),
    LPOP("左侧取出集合值", "LIST", 1, "参数格式：{KEY} {删除数量，默认1}", true),
    RPOP("右侧取出集合值", "LIST", 1, "参数格式：{KEY} {删除数量，默认1}", true),
    LLEN("获取集合长度", "LIST", 1, "参数格式：{KEY}", true),
    LRANGE("根据区间获取集合值", "LIST", 3, "参数格式：{KEY} {起始索引} {结束索引}", true),
    LINDEX("根据索引集合值", "LIST", 3, "参数格式：{KEY} {索引}", true),
    LREM("删除集合中指定个数的某个值", "LIST", 3, "参数格式：{KEY} {数量, 默认1} {值}", true),

    HGET("获取HASH值",  "HASH", 2, "参数格式：{KEY} {字段KEY:字段值}...", true),
    HSET("添加HASH字段",  "HASH", 3, "参数格式：{KEY} {NX/-} {字段KEY:字段值}...", true),
    HDEL("删除HASH字段",  "HASH", 2, "参数格式：{KEY} {字段KEY}...", true),

    SADD("设置SET集合值",  "SET", 2, "参数格式：{KEY} {值}...", true),
    SMEMBERS("展示SET集合",  "SET", 1, "参数格式：{KEY}", true),
    SISMEMBER("检查SET集合是否存在某个值（返回1/0）",  "SET", 2, "参数格式：{KEY} {值}", true),
    SREM("删除SET集合值（返回1/0）",  "SET", 1, "参数格式：{KEY} {值}...", true),
    SCARD("获取SET集合长度",  "SET", 1, "参数格式：{KEY}", true),
    SRANDMEMBER("随机返回指定个数的值",  "SET", 1, "参数格式：{KEY} {数量}", true),
    SPOP("随机取出指定个数的值",  "SET", 1, "参数格式：{KEY} {数量}", true),
    SMOVE("将某个值A集合值移动到B集合中",  "SET", 3, "参数格式：{A集合KEY} {B集合KEY} {值}", true),
    SDIFF("获取多个集合的差集",  "SET", 2, "参数格式：{KEY}...", true),
    SUNION("获取多个集合的并集",  "SET", 2, "参数格式：{KEY}...", true),
    SINTER("获取多个集合的交集",  "SET", 2, "参数格式：{KEY}...", true),

    ZADD("设置有序SET集合值",  "ZSET", 2, "参数格式：{KEY} {分数} {值}", true),
    ZCARD("获取有序SET集合长度",  "ZSET", 1, "参数格式：{KEY}", true),
    ZRANGE("展示有序SET集合",  "ZSET", 1, "参数格式：{KEY} {起始索引} {结束索引}", true),
    ZREVRANGE("逆序展示有序SET集合",  "ZSET", 1, "参数格式：{KEY} {起始索引} {结束索引}", true),
    ZRANGEBYSCORE("获取有序SET集合指定分数区间的值",  "ZSET", 1, "参数格式：{KEY} {最小分数} {最大分数}", true),
    ZSCORE("获取有序SET集合某个值的分数",  "ZSET", 1, "参数格式：{KEY} {值}", true),
    ZREM("删除有序SET集合值（返回1/0）",  "ZSET", 1, "参数格式：{KEY} {值}...", true),
    ZINCRBY("增加有序SET集合某个值的分数",  "ZSET", 1, "参数格式：{KEY} {分数} {值}", true),
    ZCOUNT("获取有序SET集合指定分数区间的值个数",  "ZSET", 1, "参数格式：{KEY} {最小分数} {最大分数}", true),
    ZMAXPOP("获取有序SET集合分数倒序取出指定个数个值",  "ZSET", 1, "参数格式：{KEY} {个数, 默认1}", true),
    ZMINPOP("获取有序SET集合分数正序取出指定个数个值",  "ZSET", 1, "参数格式：{KEY} {个数, 默认1}", true),
    ZRANK("获取某个值正序索引",  "ZSET", 1, "参数格式：{KEY} {值}", true),
    ZREVRANK("获取某个值倒序索引",  "ZSET", 1, "参数格式：{KEY} {值}", true),

    NONE("未知",  "NONE", 0, null, false);

    private String name;
    private String type;
    private String desc;
    private int minParamCount;
    private boolean isValid;

    RedisCommandTypeEnum(String name, String type, int minParamCount, String desc, boolean isValid) {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.minParamCount = minParamCount;
        this.isValid = isValid;
    }

    public static RedisCommandTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return NONE;
        }
        for (RedisCommandTypeEnum value : RedisCommandTypeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getMinParamCount() {
        return this.minParamCount;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }
}
