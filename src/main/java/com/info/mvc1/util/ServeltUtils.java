package com.info.mvc1.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServeltUtils {

    public static void setNoCacheHeader(HttpServletResponse response)
    {
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidation");
        response.setHeader("Paragma", "no-cache");
    }

    public static void setContentHtml(HttpServletResponse response){
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        setNoCacheHeader(response);

    }

    public static void setContentJson(HttpServletResponse response){
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        setNoCacheHeader(response);
    }

    public static void writeHtml(HttpServletResponse response, String title, String body) throws IOException {


        StringBuilder sbHTML = new StringBuilder();

        setContentHtml(response);

        sbHTML.append("<html lang=\"ko\">\n");

        sbHTML.append("<head>\n");
            sbHTML.append("<meta charset=\"utf-8\">\n");
            sbHTML.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
            sbHTML.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\">\n");
            sbHTML.append(String.format("<title>%s</title>\n", title));
        sbHTML.append("</head>\n");

        sbHTML.append(String.format("<body>%s</body>\n", body));

        sbHTML.append("</html>\n");

        PrintWriter writer = response.getWriter();
        writer.write( sbHTML.toString() );
    }

}
