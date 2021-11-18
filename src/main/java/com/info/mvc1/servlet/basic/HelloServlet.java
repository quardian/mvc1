package com.info.mvc1.servlet.basic;

import com.info.mvc1.util.ServeltUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @ServletComponentScan 활성화시 작동함
@WebServlet(name="helloServlet", urlPatterns = "/servlet/basic/hello")
public class HelloServlet extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        System.out.println("request = " + request );
        System.out.println(" response = " + response);
        System.out.println("username = " + username);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        ServeltUtils.writeHtml( response,
                "테스트",
                "hello " + username);
    }
}
