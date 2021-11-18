package com.info.mvc1.servlet.basic;

import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

// @ServletComponentScan 활성화시 작동함
@WebServlet(name="requestHeaderServlet", urlPatterns = "/servlet/basic/request-header")
public class RequestHeaderServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getMethod();
        String protocol = request.getProtocol();
        String scheme = request.getScheme();
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        boolean secure = request.isSecure();

        String title = "Http Request Header";
        StringBuffer bodyHtml = new StringBuffer();

        String fmtTag  = "<p>%s=%s<p/>";
        String fmtLine = "<p>------------------%s------------------<p>";
        bodyHtml.append( String.format(fmtLine, "REQUEST LINE -- START") );

        bodyHtml.append( String.format(fmtTag, "method", method) );
        bodyHtml.append( String.format(fmtTag, "method", method) );
        bodyHtml.append( String.format(fmtTag, "protocol", protocol) );
        bodyHtml.append( String.format(fmtTag, "scheme", scheme) );
        bodyHtml.append( String.format(fmtTag, "requestURL", requestURL) );
        bodyHtml.append( String.format(fmtTag, "requestURI", requestURI) );
        bodyHtml.append( String.format(fmtTag, "queryString", queryString) );
        bodyHtml.append( String.format(fmtTag, "secure", secure) );

        bodyHtml.append( String.format(fmtLine, "REQUEST LINE -- END") );

        // 모든 헤더정보
        bodyHtml.append( String.format(fmtLine, "HEADER -- START") );

        Enumeration<String> headerNames = request.getHeaderNames();
        while( headerNames.hasMoreElements()){
            String headerName  = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            bodyHtml.append( String.format(fmtTag, headerName, headerValue) );
        }

        bodyHtml.append( String.format(fmtLine, "HEADER -- END") );


        ServeltUtils.writeHtml( response, title, bodyHtml.toString() );
    }
}
