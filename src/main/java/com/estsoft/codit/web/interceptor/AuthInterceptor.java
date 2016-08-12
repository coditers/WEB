package com.estsoft.codit.web.interceptor;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.annotation.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by estsoft on 2016-07-13.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if( handler instanceof HandlerMethod == false ) {
            return true;
        }

        Auth auth = ( (HandlerMethod) handler ).getMethodAnnotation( Auth.class );

        // method without @Auth
        if( auth == null ) {
            return true;
        }

        //method with @Auth. need to check logged in
        //session exist?
        HttpSession session = request.getSession();
        if( session == null ) {
            response.sendRedirect( request.getContextPath() + "/view/client/signinform" );
            return false;
        }
        //authClient variable exist?
        ClientVo authUser = (ClientVo)session.getAttribute( "authClient" );
        if( authUser == null ) {
            response.sendRedirect( request.getContextPath() + "/view/client/signinform" );
            return false;
        }

        //authentication success
        return true;
    }

}
