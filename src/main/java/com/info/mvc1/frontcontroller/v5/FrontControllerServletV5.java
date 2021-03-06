package com.info.mvc1.frontcontroller.v5;

import com.info.mvc1.domain.ModelView;
import com.info.mvc1.domain.MyHandlerAdapter;
import com.info.mvc1.domain.MyView;
import com.info.mvc1.frontcontroller.adapter.ControllerV3HandlerAdapter;
import com.info.mvc1.frontcontroller.adapter.ControllerV4HandlerAdapter;
import com.info.mvc1.frontcontroller.v3.controller.MemberFormControllerV3;
import com.info.mvc1.frontcontroller.v3.controller.MemberListControllerV3;
import com.info.mvc1.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.info.mvc1.frontcontroller.v4.ControllerV4;
import com.info.mvc1.frontcontroller.v4.controller.MemberFormControllerV4;
import com.info.mvc1.frontcontroller.v4.controller.MemberListControllerV4;
import com.info.mvc1.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Front Controller
 */
@WebServlet(name="frontControllerServletV5", urlPatterns = "/font-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/font-controller/v5/v3/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/font-controller/v5/v3/save",   new MemberSaveControllerV3());
        handlerMappingMap.put("/font-controller/v5/v3/list",   new MemberListControllerV3());

        handlerMappingMap.put("/font-controller/v5/v4/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/font-controller/v5/v4/save",   new MemberSaveControllerV4());
        handlerMappingMap.put("/font-controller/v5/v4/list",   new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add( new ControllerV3HandlerAdapter() );
        handlerAdapters.add( new ControllerV4HandlerAdapter() );
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // [01] ????????? ??????
        Object handler = getHandler(request);

        if ( handler == null ){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // [02] ????????? ????????? ??????
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // [03] ????????? ??????
        ModelView mv = adapter.handle(request, response, handler);

        // [04] viewResolver ?????? -> View??????
        MyView myView   = viewResolver(mv.getViewName());
        
        // [05] render ??????
        myView.render(mv.getModel(), request,response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if ( adapter.supports(handler) )
                return adapter;
        }
        throw new IllegalArgumentException("handler adapter??? ?????? ??? ????????????. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object  handler  = handlerMappingMap.get(requestURI);
        return handler;
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
