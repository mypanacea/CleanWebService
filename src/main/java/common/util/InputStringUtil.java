package common.util;

import common.RequestType;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static common.RequestType.GET;
import static common.RequestType.valueOf;
import static common.util.InputStringUtil.ParameterParser.parseGetRequestParameter;
import static common.util.InputStringUtil.ParameterParser.parsePostRequestParameter;

public interface InputStringUtil {

    default String parseRequestMapping(final String inputData) {
        return inputData.split((" "))[1];
    }

    default RequestType parseRequestType(final String source) {
        return valueOf(source.split(("/"))[0].trim());
    }

    default Map<String, String> parseRequestParameter(final String source) {
        if (parseRequestType(source) == GET) {
            return parseGetRequestParameter(source);
        } else {
            return parsePostRequestParameter(source);
        }
    }

    @SuppressWarnings("unused")
    class ParameterParser {
        static Map<String, String> parseGetRequestParameter(final String source) {
            final Map<String, String> parameterMap = new HashMap<>();
            if(source.contains("?")){
                final String parameterBlock = source.substring(source.indexOf("?") + 1, source.indexOf("HTTP")).trim();
                for (final String s : parameterBlock.split(Pattern.quote("&"))) {
                    parameterMap.put(s.split(Pattern.quote("="))[0], s.split(Pattern.quote("="))[1]);
                }

            }
            return parameterMap;
        }

        static Map<String, String> parsePostRequestParameter(final String source) {
            return new HashMap<>();
        }
    }
}
