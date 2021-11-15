package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //NOTE: RequestDispatcher 란?
    //- 현재 request 에 담긴 정보를 저장하고 있다가, 그 다음 페이지 , 다다음 페이지에서도 해당 정보를 볼 수 있게 계속 저장하는 기능
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }


    // NOTE: V3부터 model 추가.

    //  V2 까지는 model 을 사용하지 않았기 때문에
    //  ControllerVX 인터페이스를 사용하는 컨트롤러 내에서 setAttribute 했었음

    //  model 에 있는 정보를 다음 뷰에서 계속 사용하기 위해,
    //  model 값을 꺼내서 request.setAttribute 에 다 넣음 (request 임. 주의!)

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
