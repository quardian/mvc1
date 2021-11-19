package com.info.mvc1.servlet.basic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.mvc1.domain.HelloData;
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

// @ServletComponentScan 활성화시 작동함
@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/servlet/basic/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String title = "HTTP BODY STRING";
        StringBuffer bodyHtml = new StringBuffer();

        String fmtTag  = "<p>%s=%s<p/>";
        String fmtLine = "<p>------------------%s------------------<p>";

        bodyHtml.append( String.format(fmtLine, "HTTP BODY JSON -- START") );

        ServletInputStream is = request.getInputStream();
        String messageBody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);

        //POST http://localhost:8080/servlet/basic/request-body-json {"username":"leeinho", "age":"20"}
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        bodyHtml.append( String.format(fmtTag, "helloData class", helloData) );

        bodyHtml.append( String.format(fmtLine, "HTTP BODY JSON -- END") );

        ServeltUtils.writeHtml( response, title, bodyHtml.toString() );
    }
}
