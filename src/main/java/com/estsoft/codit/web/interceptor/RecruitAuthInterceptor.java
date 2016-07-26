package com.estsoft.codit.web.interceptor;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * interceptor which catches all requests to recruit controller.
 * check the client is authorized or not to recruit page.
 */
public class RecruitAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RecruitService recruitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //logged in user?
        HttpSession session = request.getSession();
        if( session == null ) {
            response.sendRedirect( request.getContextPath() + "/client/signinform" );
            return false;
        }
        ClientVo authUser = (ClientVo)session.getAttribute( "authClient" );
        if( authUser == null ) {
            response.sendRedirect( request.getContextPath() + "/client/signinform" );
            return false;
        }


        //Fetch recruit id from url
        String url          = request.getRequestURL().toString();
        int recruitIdPos    = url.indexOf("recruit/");
        String recruitId    = url.substring(recruitIdPos).split("/")[1];


        //check the recruit id belongs to authClient's client id
        if(recruitService.isContained( Integer.parseInt(recruitId), authUser.getId() ) == false){
            response.sendRedirect( request.getContextPath() + "/");
            return false;
        }

        return true;
    }

}
