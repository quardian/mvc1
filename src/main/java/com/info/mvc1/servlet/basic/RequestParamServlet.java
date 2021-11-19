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
@WebServlet(name="requestParamServlet", urlPatterns = "/servlet/basic/request-param")
public class RequestParamServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = "HTTP REQUEST PARAM";
        StringBuffer bodyHtml = new StringBuffer();

        String fmtTag  = "<p>%s=%s<p/>";
        String fmtLine = "<p>------------------%s------------------<p>";

        bodyHtml.append( String.format(fmtLine, "REQUEST PARAM -- START") );

        Enumeration<String> parameterNames = request.getParameterNames();
        Iterator<String> iterator = parameterNames.asIterator();

        iterator.forEachRemaining( parameterName ->
        {
            String paramterValue = request.getParameter(parameterName);

            bodyHtml.append( String.format(fmtTag, parameterName, paramterValue) );

            // age=10&age=20
            String[] parameterValues = request.getParameterValues(parameterName);
            if ( parameterValues != null ) {
                String joinValues = String.join("/", parameterValues);
                bodyHtml.append( String.format(fmtTag, "parameterValues", joinValues) );
            }
        });

        bodyHtml.append( String.format(fmtLine, "REQUEST PARAM -- END") );

        ServeltUtils.writeHtml( response, title, bodyHtml.toString() );
    }
}
