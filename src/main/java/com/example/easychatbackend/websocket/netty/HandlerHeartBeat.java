package com.example.easychatbackend.websocket.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HandlerHeartBeat extends ChannelDuplexHandler {
    private static final Logger logger = LoggerFactory.getLogger(HandlerHeartBeat.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent e = (IdleStateEvent) evt;
        Channel channel = ctx.channel();
        Attribute<String> attribute = channel.attr(AttributeKey.valueOf(channel.id().toString()));

        String userID = attribute.get();

        if (e.state() == IdleState.READER_IDLE) {
            logger.info(" >>> " + userID + " 一定时间未发任何消息,断开连接");
            ctx.close();
        } else if (e.state() == IdleState.WRITER_IDLE) {
            ctx.writeAndFlush("Alive");
        }

    }
}
