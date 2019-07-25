package common.config;

public class MainConfig {
    public class HttpConfig {

        public static final String SUCCESS = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: ";

        public static final String ERROR = "HTTP 400 Bad Request";

        public static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

        public static final String ERROR_MESSAGE = "Запрашиваемый URL не найден!";

        private HttpConfig() {
        }
    }
}
