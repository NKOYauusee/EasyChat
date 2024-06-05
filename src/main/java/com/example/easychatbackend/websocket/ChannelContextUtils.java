package com.example.easychatbackend.websocket;


import com.example.easychatbackend.utils.RedisService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component("ChannelContextUtils")
public class ChannelContextUtils {

    private static final ConcurrentHashMap<String, Channel> USER_CONTEXT_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelGroup> GROUP_CONTEXT_MAP = new ConcurrentHashMap<>();

    private static Logger logger = LoggerFactory.getLogger(ChannelContextUtils.class);

    @Resource
    RedisService redisService;

    /**
     * 给连接上的 channel 绑定用户的userID
     */
    public void addContext(String userID, Channel channel) {
        String channelId = channel.id().toString();
        logger.info(" >>>> CHANNEL ID: " + channelId);
        AttributeKey attributeKey = null;

        if (!attributeKey.exists(channelId)) {
            attributeKey = AttributeKey.newInstance(channelId);
        } else {
            attributeKey = AttributeKey.valueOf(channelId);
        }

        channel.attr(attributeKey).set(userID);

        USER_CONTEXT_MAP.put(userID, channel);
        redisService.saveHeartBeat(userID);

        String groupID = "10000";
        addGroup(groupID,channel);
    }


    private void addGroup(String groupId, Channel channel) {
        ChannelGroup group = GROUP_CONTEXT_MAP.get(groupId);

        if (group == null) {
            group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            GROUP_CONTEXT_MAP.put(groupId, group);
        }

        if (channel == null)
            return;

        group.add(channel);
    }


    public void sendGroup(String msg) {
        ChannelGroup group = GROUP_CONTEXT_MAP.get("10000");
        group.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
