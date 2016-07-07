package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  /** Forwards signupform.jsp */
  @RequestMapping("/signupform")
  public String signupform() {

    return "client/_signupform";

  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup( @ModelAttribute ClientVo clientVo) {

    /* TODO - Back-end validation Using Hibernate validator '@valid'
    public String signup(@ModelAttribute @Valid ClientVo clientVo,
                         BindingResult result,
                         Model model) {

      //ERROR -> error
      if (result.hasErrors()) {

        model.addAllAttributes( result.getMode() );
        return "/client/signupform";
      }

      int id = clientService.signup( clientVo );
      return "redirect:/client/signupsuccess/" + id;
    }
     */

    clientService.signup(clientVo);
    int id = clientVo.getId();
    return "redirect:/client/signupsuccess/" + id;
  }

  /**
   * Checks whether the sign up process was succeeded or not. If it was succeeded,
   * forwards 'signup-success.jsp' file to end-user, if failed, '@code signup-fail.jsp'.
   */
  @RequestMapping("/signupsuccess/{id}")
  public String signupsuccess( @PathVariable("id") int id, Model model ) {

    ClientVo clientVo = clientService.getVoById(id);

    model.addAttribute( "clientVo", clientVo );

    if (clientVo != null) {
      return "client/signup-success";
    } else {
      return "client/signup-fail";
    }
  }

  /** Forwards signinform.jsp to user. */
  @RequestMapping("/signinform")
  public String signinform() {
    return "client/_signinform";
  }


  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public String signin(@ModelAttribute ClientVo clientVo, HttpServletRequest request, Model model) {

    // Get authenticate user with username and password using a database.
    clientVo = clientService.validsignin( clientVo );
    // Check authenticate user
    if(clientVo == null) {
      // auth failed
      model.addAttribute("auth", false); //jsp 에서 auth fail이면 다시 입력하라는 문구 출력
      return "client/_signinform";
    }
    System.out.println("\n\n\n\n\n\n\n\n sign in clientVo : " + clientVo);
    //auth success
    HttpSession session = request.getSession(true);
    session.setAttribute("authClient", clientVo);
    return "redirect:/main/";
  }


  @RequestMapping("/signout")
  public String signout( HttpServletRequest request ) {

    HttpSession session = request.getSession();

    if( session != null){
      session.removeAttribute( "authUser" );
      session.invalidate();
    }
    return "redirect:/main/";
  }

  /**
   * Response Body Test.
   */
  @RequestMapping( "/hello" )
  @ResponseBody
  public String hello() {
    return "Hello, Codit.";
  }

}
