package com.info.mvc1.springmvc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * #application.property
 * spring.mvc.view.prefix=/WEB-INF/views/
 * spring.mvc.view.suffix=.jsp
 *
 * 뷰 리졸버 - InternalResourceViewResolver
 *  Spring Boot는 InternalResourceViewResolver 라는 뷰 리졸버를 자동으로 등록하는데,
 *  이떄 application.properties 에 spring.mvc.view.prefix, spring.mvc.view.suffix 설정
 *  정보를 사용해서 등록한다.
 *
 * 1 = BeanNameViewResolver             : 빈 이름으로 뷰를 찾아서 반환. (예: 엑셀 파일 생성 기능에 사용 )
 * 2 = InternalResourceViewResolver     : JSP를 처리할 수 있는 뷰를 반환
 */
@Component("/springmvc/resolver-controller")
public class ResolverController implements Controller
{
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("#####ResolverController.handleRequest");
        return new ModelAndView("new-form");
        //return new ModelAndView("/WEB-INF/views/new-form.jsp");
    }
}
