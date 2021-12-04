package com.info.mvc1.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 HTTP message body에 데이터를 직접 담아서 요청
 - HTTP API에서 주로 사용 JSON, XML, TEXT (주로 json 사용)
 - POST, PUT, PATCH

 요청 파라미터와 다르게, HTTP 메시지 바디를 통해 데이터가 직접 넘어오는 경우는
 @ReuqestParam, @ModelAttribte 사용할 수 없다


 */

@Slf4j
@RestController
public class ReuqestBodyController
{

    @RequestMapping( value="/springmvc/basic/requestbody/string-v1", method = RequestMethod.POST)
    public String stringV1(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagebody={}", messageBody);

        return messageBody;
    }

    @RequestMapping( value="/springmvc/basic/requestbody/string-v2", method = RequestMethod.POST)
    public String stringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messagebody={}", messageBody);

        return messageBody;
    }

    /**
     * HHTP MessageConver를 이용해서 더 쉽게
     * HttpEntity : HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회
     * - 요청 파라미터를 조회하는 기능과 관계없음
     * HttpEntity는 응답에도 사용
     * - 메시지 바디 정보 직접 반환
     * - 헤더 정보 포함 가능
     * - view 조회 X
     *
     * HttpEntity 를 상속받은 다음 객체들도 같은 기능 제공
     * RequestEntity
     * - HttpMethod, url 정보가 추가, 요청에서 사용
     * ResponseEntity
     * - Http 상태코드 설정가능, 응답에 사용
     * - return new ResponseEntity<String>("string body", responseHeaders, HttpStatus.CREATED)
     *
     * @param httpEntity
     * @return
     * @throws IOException
     */
    @RequestMapping( value="/springmvc/basic/requestbody/string-v3", method = RequestMethod.POST)
    public HttpEntity<String> stringV3(HttpEntity<String> httpEntity) throws IOException {
        HttpHeaders headers = httpEntity.getHeaders();
        String body = httpEntity.getBody();
        log.info("messagebody={}", body);

        return new HttpEntity<>(body);
    }

    @RequestMapping( value="/springmvc/basic/requestbody/string-v4", method = RequestMethod.POST)
    public ResponseEntity<String> stringV4(RequestEntity<String> httpEntity) throws IOException {
        HttpHeaders headers = httpEntity.getHeaders();
        URI url = httpEntity.getUrl();
        String body = httpEntity.getBody();
        log.info("messagebody={}", body);

        return new ResponseEntity<String>(body, HttpStatus.CREATED);
    }

    /**
     * @RequestBody를 사용하면 메시지 바디 정보를 편리하게 조회할 수 있다.
     * @ResponseBody를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다.
     * @param messageBody
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping( value="/springmvc/basic/requestbody/string-v5", method = RequestMethod.POST)
    public String stringV4(@RequestBody String messageBody) throws IOException {
        log.info("messagebody={}", messageBody);
        return messageBody;
    }
}
