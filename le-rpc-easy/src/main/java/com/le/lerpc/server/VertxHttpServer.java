package com.le.lerpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        //创建Vertx实例
        Vertx vertx = Vertx.vertx();
        //创建Http服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        //监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

//        server.requestHandler(request -> {
//            // 处理 HTTP 请求
//            System.out.println("Received request: " + request.method()+" " + request.uri());
//
//            // 发送 HTTP 相应
//
//            request.response()
//                    .putHeader("Content-Type", "text/plain")
//                    .end("Hello from vert.x HTTP server!");
//        });

        // 启动 HTTP 服务器并见挺指定端口
        server.listen(port,result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port: " + port);
            } else {
                System.out.println("Failed to start server: "+ result.cause());
            }
        });


    }
}
