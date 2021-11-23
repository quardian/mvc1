package com.info.mvc1.frontcontroller.v1;

import com.info.mvc1.frontcontroller.v1.controller.MemberFormControllerV1;
import com.info.mvc1.frontcontroller.v1.controller.MemberListControllerV1;
import com.info.mvc1.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller
 */
@WebServlet(name="frontControllerServletV1", urlPatterns = "/font-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/font-controller/v1/new-form", new MemberFormControllerV1());
        controllerMap.put("/font-controller/v1/save",   new MemberSaveControllerV1());
        controllerMap.put("/font-controller/v1/list",   new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);
        if ( controller != null ){
            controller.process(request, response);
        }else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
