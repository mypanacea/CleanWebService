package web.model;

import java.util.Map;

public class BookRequest {
    Map<String, String> paramMap;

    public BookRequest(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }
}
