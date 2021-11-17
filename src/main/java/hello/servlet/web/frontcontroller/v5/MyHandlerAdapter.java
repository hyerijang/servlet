package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MyHandlerAdapter {

    /**
     * 어댑터가 해당 컨트롤러를 처리 할 수 있는지 판단하는 메서드
     *
     * @param handler
     * @return 지원유무
     */
    boolean supports(Object handler);

    /**
     * 어댑터는 실제 컨트롤러를 호출하고, 그 결과로 ModelView 를 반환한다
     * - 실제 컨트롤러가 ModelView 를 반환하지 못하면, 어댑터가 생성해서라도 반환해야함
     * - 프론트 컨트롤러가 실제 컨트롤러를 호출했지만, 이제는 어댑터를 통해서 실제 컨트롤러가 호출된다.
     *
     * @param request
     * @param response
     * @param handler
     * @return modelView
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler);

}
