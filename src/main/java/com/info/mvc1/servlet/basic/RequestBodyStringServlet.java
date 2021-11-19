package com.info.mvc1.servlet.basic;

import com.info.mvc1.util.ServeltUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;

// @ServletComponentScan 활성화시 작동함
@WebServlet(name="requestBodyStringServlet", urlPatterns = "/servlet/basic/request-body-string")
public class RequestBodyStringServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = "HTTP BODY STRING";
        StringBuffer bodyHtml = new StringBuffer();

        String fmtTag  = "<p>%s=%s<p/>";
        String fmtLine = "<p>------------------%s------------------<p>";

        bodyHtml.append( String.format(fmtLine, "HTTP BODY STRING -- START") );

        ServletInputStream is = request.getInputStream();
        String messageBody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);

            bodyHtml.append( String.format(fmtTag, "messageBody", messageBody) );

        bodyHtml.append( String.format(fmtLine, "HTTP BODY STRING -- END") );

        ServeltUtils.writeHtml( response, title, bodyHtml.toString() );
    }
}
