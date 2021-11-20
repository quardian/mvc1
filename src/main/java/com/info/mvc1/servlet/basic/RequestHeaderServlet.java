package com.info.mvc1.servlet.basic;

import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

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
        /*
        while( headerNames.hasMoreElements()){
            String headerName  = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            bodyHtml.append( String.format(fmtTag, headerName, headerValue) );
        }
        */

        // required Java 1.9 over
        Iterator<String> headerInterator = headerNames.asIterator();
        headerInterator.forEachRemaining(headerName -> {
            String headerValue = request.getHeader(headerName);
            bodyHtml.append( String.format(fmtTag, headerName, headerValue) );
        });

        bodyHtml.append( String.format(fmtLine, "HEADER -- END") );

        // 모든 쿠키정보
        bodyHtml.append( String.format(fmtLine, "COOKIE -- START") );
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ){
            for (Cookie cookie : cookies) {
                bodyHtml.append( String.format(fmtTag, cookie.getName(), cookie.getValue()) );
            }
        }
        bodyHtml.append( String.format(fmtLine, "COOKIE -- END") );

        // 컨텐츠 편의조회
        bodyHtml.append( String.format(fmtLine, "BODY 컨텐츠 편의조회 -- START") );
        String contentType = request.getContentType();
        int contentLength = request.getContentLength();
        String characterEncoding = request.getCharacterEncoding();
            bodyHtml.append( String.format(fmtTag, "contentType", contentType) );
            bodyHtml.append( String.format(fmtTag, "contentLength", contentLength) );
            bodyHtml.append( String.format(fmtTag, "characterEncoding", characterEncoding) );
        bodyHtml.append( String.format(fmtLine, "BODY 컨텐츠 편의조회 -- END") );

        // 컨텐츠 편의조회
        bodyHtml.append( String.format(fmtLine, "ETC -- START") );
        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        int remotePort = request.getRemotePort();

        String localName = request.getLocalName();
        String localAddr = request.getLocalAddr();
        int localPort = request.getLocalPort();

        //-Djava.net.preferIPv4Stack=true
            bodyHtml.append( String.format(fmtTag, "remoteHost", remoteHost) );
            bodyHtml.append( String.format(fmtTag, "remoteAddr", remoteAddr) );
            bodyHtml.append( String.format(fmtTag, "remotePort", remotePort) );

            bodyHtml.append( String.format(fmtTag, "localName", localName) );
            bodyHtml.append( String.format(fmtTag, "localAddr", remoteHost) );
            bodyHtml.append( String.format(fmtTag, "localPort", localPort) );


        bodyHtml.append( String.format(fmtLine, "ETC -- END") );



        ServeltUtils.writeHtml( response, title, bodyHtml.toString() );
    }
}
