package com.example.easychatbackend.websocket.netty;

import com.example.easychatbackend.configuration.AppConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateHandler;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component("NettyWebSocketStarter")
public class NettyWebSocketStarter extends ServerBootstrap {
    @Resource
    AppConfig appConfig;

    @Resource
    HandlerWebSocket webSocket;
    private static Logger logger = LoggerFactory.getLogger(NettyWebSocketStarter.class);
    private static EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private static EventLoopGroup workGroup = new NioEventLoopGroup(1);


    @PreDestroy
    public void destroy() {
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


    @Async
    public void startNetty() {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();

                        pipeline.addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(64 * 1024))
                                .addLast(new IdleStateHandler(60, 0, 0, TimeUnit.SECONDS))
                                .addLast(new HandlerHeartBeat())
                                .addLast(new WebSocketServerProtocolHandler("/ws", null, true, 64 * 1024, true, true, 10000L))
                                .addLast(webSocket);
                    }
                });

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(appConfig.getWsPort()).sync();
            logger.info("Netty启动成功, 端口：" + appConfig.getWsPort());
            // 监听关闭事件
            channelFuture.channel().closeFuture().addListener(future -> {
                bossGroup.shutdownGracefully();
                workGroup.shutdownGracefully();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
