package web.component;

import web.controller.BookController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpRequestSocket {
    private static Socket socket;

    private HttpRequestSocket() {
    }

    public static Socket getInstance() throws IOException {
        if (socket == null) {
            synchronized (BookController.class) {
                if (socket == null) {
                    socket = new ServerSocket(9001).accept();
                }
            }
        }

        return socket;
    }
}
