package com.estsoft.codit.web.interceptor;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

  @Autowired()
  private ClientService clientService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    ClientVo clientVo = new ClientVo();
    clientVo.setEmail(email);
    clientVo.setPassword(password);
    ClientVo authClient = clientService.getClientVoByEmailPassword(clientVo);
    HttpSession session = request.getSession(true);

    // authentication fail
    if(authClient==null) {
      response.sendRedirect( "/client/signinform" );
      return false;
    }

    //authentication success!
    //create session and set 'authClient' variable on session context
    session.setAttribute("authClient", authClient);
    response.sendRedirect( "/" );
    return false;
  }
}
