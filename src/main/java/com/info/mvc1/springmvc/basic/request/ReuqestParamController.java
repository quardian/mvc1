package com.info.mvc1.springmvc.basic.request;

import com.info.mvc1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class ReuqestParamController
{

    /**
     * http://localhost:8080/springmvc/basic/request/param-v1?username=leeinho&age=100
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v1")

    public String paramV1(HttpServletRequest request,
                          HttpServletResponse response)
    {
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        log.info("username={}, age={}", username, age);

        return "OK";
    }


    @RequestMapping("/springmvc/basic/request/param-v2")

    public String paramV2(@RequestParam("username") String username,
                          @RequestParam("age") int age)
    {
        log.info("username={}, age={}", username, age);

        return "OK";
    }

    /**
     * @RequestParam(value="" 생략) : 변수명이랑 파라메타 명이랑 대신 같아야 한다.
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v3")
    public String paramV3(@RequestParam String username,
                          @RequestParam int age)
    {
        log.info("username={}, age={}", username, age);

        return "OK";
    }

    /**
     * @RequestParam 자체도 생략 가능
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v4")
    public String paramV4(String username, int age)
    {
        log.info("username={}, age={}", username, age);

        return "OK";
    }

    /**
     * required = true 인데 값이 없으면 400 에러 발생
     * int 형 값에는 null 이 들어갈 수 없어서 defaultValue로 지정하거나, Integer 형으로 받아야 한다.
     * defaultValue는 빈문자도 defaultValue 값으로 대체해준다.
     * @param username
     * @param age
     * @return
     */
    @RequestMapping("/springmvc/basic/request/required")
    public String required( @RequestParam(required = false, defaultValue = "이름없음") String username,
                           @RequestParam(required = false, defaultValue = "10") int age)
    {
        log.info("username={}, age={}", username, age);

        return "OK";
    }


    @RequestMapping("/springmvc/basic/request/map")
    public String map( @RequestParam Map<String, Object> paramMap)
    {
        Object username = paramMap.get("username");
        Object age      = paramMap.get("age");

        log.info("username={}, age={}", username, age);

        return "OK";
    }

    /**
     * MutlValueMap(key=[value1, value2], ..])
     * @param paramMap
     * @return
     */
    @RequestMapping("/springmvc/basic/request/multivaluemap")
    public String multivaluemap( @RequestParam MultiValueMap<String,String> paramMap)
    {
        String username = paramMap.getFirst("username");
        String age      = paramMap.getFirst("age");

        log.info("username={}, age={}", username, age);

        return "OK";
    }


    /**
     * Spring MVC는 @ModelAttribute가 있으면 다음을 실행한다.
     * Member 객체 생성
     * 요청 파라미터 이름으로 Member 객체의 프로퍼티를 찾는다 ( 프로퍼티의 setter 호출 )
     * 예) 파라미터명이 username 이면, setUsername 메소드를 찾아서 호출
     * 바인딩 오류 (예를 들어 age는 숫자가 들어가야 하나 문자가 들어오면 ) BindException이 발생한다.
     * 바인딩 오류 처리하는 방법은 검증 부분
     * 
     * @param member
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v5")

    public String paramV5(@ModelAttribute Member member)
    {
        log.info("username={}, age={}", member.getUsername(), member.getAge());

        return "OK";
    }


    /**
     * @ModelAttribute 생략 가능
     *
     * @RequestParam, @ModelAttribute 모두 생략시
     * 스프링은 String, int, Integer 같은 단순 타입은 @RequestParam
     * 그 외는 @ModelAttribute ( argument resolver로 지정해둔 타입은 예외)
     * @param member
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v6")

    public String paramV6(Member member)
    {
        log.info("username={}, age={}", member.getUsername(), member.getAge());

        return "OK";
    }


    /**
     * is=true인경우 : true, 1, yes
     * @param is
     * @return
     */
    @RequestMapping("/springmvc/basic/request/param-v7")

    public String paramV7(@RequestParam boolean is)
    {
        log.info("is={}", is);

        return "OK";
    }
}
