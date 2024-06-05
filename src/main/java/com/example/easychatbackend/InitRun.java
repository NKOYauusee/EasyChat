package com.example.easychatbackend;

import com.example.easychatbackend.websocket.netty.NettyWebSocketStarter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component("InitRun")
public class InitRun implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(InitRun.class);
    @Resource
    private DataSource dataSource;

    @Resource
    NettyWebSocketStarter nettyWebSocketStarter;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            dataSource.getConnection();
            nettyWebSocketStarter.startNetty();
        } catch (SQLException e) {
            throw new RuntimeException("连接失败!!!", e);
        }
    }
}
