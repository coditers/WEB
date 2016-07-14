package com.estsoft.codit.web.interceptor;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * recruit controller에 접근하는 모든 request가 지나는 interceptor
 */
public class RecruitAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RecruitService recruitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //session이 있나?
        HttpSession session = request.getSession();
        if( session == null ) {
            response.sendRedirect( request.getContextPath() + "/client/signinform" );
            return false;
        }
        //login은 했나?
        ClientVo authUser = (ClientVo)session.getAttribute( "authClient" );
        if( authUser == null ) {
            response.sendRedirect( request.getContextPath() + "/client/signinform" );
            return false;
        }

        //Recruit Id를 url로부터 추출
        String url = request.getRequestURL().toString();
        int index = url.indexOf("recruit/");
        String recruitId = url.substring(index).split("/")[1];

        if(recruitService.isContained( Integer.parseInt(recruitId), authUser.getId() ) == false){
            response.sendRedirect( request.getContextPath() + "/");
            return false;
        }
        return true;
    }

}
