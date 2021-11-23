package com.info.mvc1.frontcontroller.v2;

import com.info.mvc1.frontcontroller.domain.MyView;
import com.info.mvc1.frontcontroller.v2.controller.MemberFormControllerV2;
import com.info.mvc1.frontcontroller.v2.controller.MemberListControllerV2;
import com.info.mvc1.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
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
@WebServlet(name="frontControllerServletV2", urlPatterns = "/font-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/font-controller/v2/new-form", new MemberFormControllerV2());
        controllerMap.put("/font-controller/v2/save",   new MemberSaveControllerV2());
        controllerMap.put("/font-controller/v2/list",   new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if ( controller == null ){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request,response);

    }
}
