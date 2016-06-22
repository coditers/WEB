package com.estsoft.codit.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

/**
 * A user controller manages incoming requests with path {@code /client}
 * and redirect to proper response.<p/>
 *
 * Created by JisungLim on 2016-06-20.
 */
@Controller
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  /** Forwards signupform.jsp */
  @RequestMapping("/signupform")
  public String signupform() {

    return "/client/signupform";
  }

  /** Sign up method with back-end validation */
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup(@ModelAttribute ClientVo clientVo) {

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

    int id = clientService.signup(clientVo);

    return "redirect:/client/signupsuccess/" + id;
  }

  /**
   * Checks whether the sign up process was succeeded or not. If it was succeeded,
   * forwards 'signup-success.jsp' file to end-user, if failed, '@code signup-fail.jsp'.
   */
  @RequestMapping("/signupsuccess/{id}")
  public String signupsuccess( @PathVariable("id") int id, Model model ) {

    ClientVo clientVo = clientService.getUserInfo(id);

    model.addAttribute( "clientVo", clientVo );

    if (clientVo != null) {
      return "/client/signup-success";
    } else {
      return "/client/signup-fail";
    }
  }

  /** Forwards signinform.jsp to user. */
  @RequestMapping("/signinform")
  public String signinform() {
    return "client/signinform";
  }

  /**
   * If the valid is failed, it forward signinform.jsp again including attribute
   * called 'auth' whose value is false.
   * <p/>
   * On the other hand, if the valid succeed, it will get to the main page by means
   * of redirecting request. At that time it uploads auth object on the session scope.
   * <p/>
   * @param clientVo A client VO includes email and password passed from the form.
   * @param session A session scope.
   * @param model Model.
   */
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public String signin(@ModelAttribute ClientVo clientVo, HttpSession session, Model model) {

    // Get authenticate user with username and password using a database.
    ClientVo authClient = clientService.validsignin( clientVo );

    // Check authenticate user
    if(authClient == null) {
      // auth failed
      model.addAttribute("auth", false);
      return "client/signinform";
    }

    //auth success
    session.setAttribute("authClient", authClient);
    return "redirect:/main/";
  }

  /**
   * Sign out process. Removes auth object on the session.
   *
   * @param session A session scope.
   */
  @RequestMapping("/signout")
  public String signout( HttpSession session ) {
    ClientVo authClient = (ClientVo) session.getAttribute("authClient");
    if (authClient != null) {
      session.removeAttribute( "authClient" );
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
