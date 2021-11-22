package com.info.mvc1.servlet.mvc;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServelt", urlPatterns = "/servlet/mvc/new-form")
public class MvcMemberFormServelt extends HttpServlet
{
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        
        // 다른 서블릿이나 JSP로 이동할 수 있는 기능 (서버 내부에서 재호출)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);


    }
}
