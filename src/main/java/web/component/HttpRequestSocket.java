package web.component;

import domain.service.BookService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpRequestSocket {
    private static volatile Socket socket;

    private HttpRequestSocket() {
    }

    public static Socket getInstance() throws IOException {
        if (socket == null) {
            synchronized (BookService.class) {
                if (socket == null) {
                    socket = new ServerSocket(9001).accept();
                }
            }
        }

        return socket;
    }
}
