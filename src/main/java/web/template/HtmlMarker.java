package web.template;

import domain_model.DomainBook;
import web.component.HttpRequestSocket;
import web.controller.BookController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static common.config.MainConfig.HttpConfig.*;

public class HtmlMarker {
    private static HtmlMarker htmlMarker;

    private HtmlMarker() {
    }

    public static HtmlMarker getInstance() {
        if (htmlMarker == null) {
            synchronized (BookController.class) {
                if (htmlMarker == null) {
                    htmlMarker = new HtmlMarker();
                }
            }
        }

        return htmlMarker;
    }

    public void makeTemplate(final String fileName, Map<String, List<DomainBook>> param) {
        try {
            final BufferedWriter bufferedWriter =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(HttpRequestSocket.getInstance().getOutputStream()), StandardCharsets.UTF_8));
            if (fileName.equals("error")) {
                bufferedWriter.write(ERROR + ERROR_MESSAGE.length() + OUTPUT_END_OF_HEADERS + readFile(fileName, param));
                bufferedWriter.flush();
            } else {
                bufferedWriter.write(SUCCESS + readFile(fileName, param).length() + OUTPUT_END_OF_HEADERS + readFile(fileName, param));
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String readFile(final String fileName, Map<String, List<DomainBook>> param) {
        final StringBuilder builder = new StringBuilder();
        final String path = "C:\\Users\\mypan\\IdeaProjects\\CleanWebService\\src\\resources\\pages\\" + fileName + ".html";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("${")) {
                    final String key = line.substring(line.indexOf("{") + 1, line.indexOf("}"));
                    final String keyPrefix = key.split(Pattern.quote("."))[0];
                    for (final DomainBook domainBook : param.get(keyPrefix)) {
                        builder.append("<tr>");
                        builder.append(
                                line.replace("${book.id}", domainBook.getId())
                                        .replace("${book.author}", domainBook.getAuthor())
                                        .replace("${book.title}", domainBook.getTitle())
                        ).append("</tr>");
                    }
                    if(param.get(keyPrefix).isEmpty()){
                        builder.append(line.replace("${book.id}</td><td>${book.author}</td><td>${book.title}", "<p>library is EMPTY</p>"));
                    }
                    continue;
                }
                builder.append(line).append("\n");
            }
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
