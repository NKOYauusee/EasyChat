package com.example.easychatbackend.websocket.netty;

import com.example.easychatbackend.entity.dto.TokenUserInfoDto;
import com.example.easychatbackend.utils.RedisService;
import com.example.easychatbackend.utils.StringUtils;
import com.example.easychatbackend.websocket.ChannelContextUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// 入站处理器
@Component("HandlerWebSocket")
@ChannelHandler.Sharable
public class HandlerWebSocket extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Resource
    RedisService redisService;

    @Resource
    ChannelContextUtils contextUtils;

    private static Logger logger = LoggerFactory.getLogger(HandlerWebSocket.class);


    /**
     * 初始化连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("新链接加入...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("有链接断开...");
    }

    /**
     * 处理器 接受消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        Channel channel = ctx.channel();
        Attribute<String> attr = ctx.attr(AttributeKey.valueOf(channel.id().toString()));
        String userId = attr.get();

        logger.info(" >>>>> " + userId + " 消息为：" + textWebSocketFrame.text());

        //保存心跳
        redisService.saveHeartBeat(userId);

        contextUtils.sendGroup(textWebSocketFrame.text());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete handler = (WebSocketServerProtocolHandler.HandshakeComplete) evt;

            String uri = handler.requestUri();
            String token = getToken(uri);
            if (token == null) {
                logger.info("no Token...");
                ctx.close();
                return;
            }

            TokenUserInfoDto userInfoDto = redisService.getUserInfoFromToken(token);
            if (userInfoDto == null) {
                logger.info("Wrong Token");
                ctx.close();
                return;
            }

            contextUtils.addContext(userInfoDto.getUserId(), ctx.channel());
        }

    }


    private String getToken(String uri) {
        if (StringUtils.isEmpty(uri) || !uri.contains("?"))
            return null;

        String[] queryParams = uri.split("\\?");
        if (queryParams.length != 2)
            return null;

        String[] params = queryParams[1].split("=");
        if (params.length != 2)
            return null;

        return params[1];
    }
}
