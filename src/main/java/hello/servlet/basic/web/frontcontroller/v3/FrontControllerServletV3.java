package hello.servlet.basic.web.frontcontroller.v3;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
     private Map<String, ControllerV3> controllerMap = new HashMap<>();

     public FrontControllerServletV3() {
         controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
         controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
         controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
     }

     @Override
     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String requestURI = request.getRequestURI();
         ControllerV3 controller = controllerMap.get(requestURI);

         if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
         }

         //param 가져옴
         Map<String, String> paramMap = createParamMap(request);
         ModelView mv = controller.process(paramMap);


         //논리이름 new-form 을 뷰 리졸버에 넣어서 myView 반환
         String viewName = mv.getViewName(); //물리이름을 받아옴
         MyView view = viewResolver(viewName); //모델도 render 에 넘겨줘야 함
         view.render(mv.getModel(), request, response); //뷰가 렌더링 되려면 모델 정보가 필요해서
     }

     private Map<String, String> createParamMap(HttpServletRequest request) {
         Map<String, String> paramMap = new HashMap<>();
         request.getParameterNames().asIterator()
                 .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
         return paramMap;
     }

     private MyView viewResolver(String viewName) { //바뀌면 얘만 바꾸면 됨, 컨트롤러는 가만히
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
     }
}