package com.estsoft.codit.web.interceptor;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.Annotation.Auth;
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
        // @Auth 가 없는 컨트롤러 핸들러
        // 접근 제어가 필요 없음
        if( auth == null ) {
            return true;
        }
        //접근 제어 (인증이 필요함)
        HttpSession session = request.getSession();
        if( session == null ) {
            response.sendRedirect( request.getContextPath() + "/view/client/_signinform" );
            return false;
        }
        ClientVo authUser = (ClientVo)session.getAttribute( "authClient" );
        if( authUser == null ) {
            response.sendRedirect( request.getContextPath() + "/view/client/_signinform" );
            return false;
        }
        // 인증된 사용자
        return true;
    }
}
