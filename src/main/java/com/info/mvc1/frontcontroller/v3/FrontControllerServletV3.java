package com.info.mvc1.frontcontroller.v3;

import com.info.mvc1.domain.ModelView;
import com.info.mvc1.frontcontroller.domain.MyView;
import com.info.mvc1.frontcontroller.v2.ControllerV2;
import com.info.mvc1.frontcontroller.v2.controller.MemberFormControllerV2;
import com.info.mvc1.frontcontroller.v2.controller.MemberListControllerV2;
import com.info.mvc1.frontcontroller.v2.controller.MemberSaveControllerV2;
import com.info.mvc1.frontcontroller.v3.controller.MemberFormControllerV3;
import com.info.mvc1.frontcontroller.v3.controller.MemberListControllerV3;
import com.info.mvc1.frontcontroller.v3.controller.MemberSaveControllerV3;

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
@WebServlet(name="frontControllerServletV3", urlPatterns = "/font-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/font-controller/v3/new-form", new MemberFormControllerV3());
        controllerMap.put("/font-controller/v3/save",   new MemberSaveControllerV3());
        controllerMap.put("/font-controller/v3/list",   new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if ( controller == null ){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 논리 이름
        MyView myView   = viewResolver(viewName);

        myView.render(mv.getModel(), request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName ->
                paramMap.put(paramName, request.getParameter(paramName))
        );
        return paramMap;
    }
}
