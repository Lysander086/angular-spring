package com.example.financeGatewayDemo;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TcpServer {
    public static void main(String[] args) {

    }

    public void startServer() {
        Vertx vertx = Vertx.vertx();
        NetServer netServer = vertx.createNetServer();
//        netServer.connectHandler(new ConnHandler());
        netServer.listen(8091, res -> {
            if (res.succeeded()) {
                log.info("gateway startup success at port 8091 ");

            } else {
                log.error("gateway startup fail", res.cause());

            }
        });
    }
//    public  class ConnHandler implements Handler
}
