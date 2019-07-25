package web.component;

import common.util.InputStringUtil;
import web.controller.BookController;
import web.template.HtmlMarker;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

import static java.util.Collections.emptyMap;


public class HttpRequestPreProcessor implements InputStringUtil {

    private final byte[] BYTE_BUFFER = new byte[1024];

    public void doRequest() {
        try {
            while (true) {
                System.out.println("Socket open");
                final Socket socket = HttpRequestSocket.getInstance();
                final DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                final String inputUrl = new String(BYTE_BUFFER, 0, in.read(BYTE_BUFFER));
                processRequest(inputUrl);
                System.out.println("send request " + inputUrl);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(final String inputData) {
        final String urlMapping = parseRequestMapping(inputData);
        final Map<String, String> paramMap = parseRequestParameter(inputData);
        final Method[] methods = BookController.getInstance().getClass().getMethods();
        for (final Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class) && urlMapping.contains(method.getAnnotation(RequestMapping.class).path())) {
                try {
                    method.invoke(BookController.getInstance(), paramMap);
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        HtmlMarker.getInstance().makeTemplate("error", emptyMap());
    }
}