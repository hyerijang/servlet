package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParmaServlet", urlPatterns = "/request-param")
public class RequestParmaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParmaServlet.service");
        System.out.println("[전체 파라미터 조회] - start");
        // 주의 : 이름 같은 복수 파라미터는 조회되지 않는다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        //보통 전체 파라미터보다 단일 파라미터 조회하는 경우가 많음
        System.out.println("[단일 파라미터 조회] - start");
        // TIP : request.getParameter("username");까지 치고  [ctrl alt v] 하면 앞부분 자동완성됨
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        // http://localhost:8080/request-param?username=hello&age=20&username=hello2
        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        // TIP : iter 치면 for문 자동으로 뜸
        for (String name : usernames) {
            System.out.println("username = " + name);
        }


        response.getWriter().write("ok");

    }
}
