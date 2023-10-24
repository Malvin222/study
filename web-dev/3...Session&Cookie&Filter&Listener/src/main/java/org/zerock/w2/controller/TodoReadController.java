package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void  doGet(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException, ServletException {

        try {
            Long tno = Long.parseLong(req.getParameter("tno"));

            TodoDTO todoDTO = todoService.get(tno);

            //데이터 담기 'dto'라는 이름으로 TodoDTO를 담음
            req.setAttribute("dto", todoDTO);

            //쿠키 찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            //todoListStr의 값이 있다면 exist는 true
            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0) {
                exist = true;
            }
            log.info("exit: " +exist);

            //exist가 false라면 쿠키를 생성
            if(!exist){
                todoListStr += "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60* 60* 24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
    //모든 쿠키 중 조회 목록 쿠키를 찾는 메소드
    private Cookie findCookie(Cookie[] cookies, String cookieName){

        Cookie targetCookie = null;
        //쿠키가 null이 아니라면
        if(cookies != null && cookies.length > 0){
            for (Cookie ck:cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }
        if (targetCookie == null){
            targetCookie = new Cookie(cookieName,"");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }
        return  targetCookie;
    }
}
