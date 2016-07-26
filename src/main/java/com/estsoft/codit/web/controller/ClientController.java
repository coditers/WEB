package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.web.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @RequestMapping("/signupform")
  public String signupform() { return "client/signupform"; }


  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public String signup(@ModelAttribute @Valid ClientVo clientVo, BindingResult result, Model model) {

    //invalid clientVo
    if (result.hasErrors()) {
      model.addAllAttributes( result.getModel() );
      return "/client/signupform";
    }

    //register client
    clientService.registerClient(clientVo);
    int clientId = clientVo.getId(); //after insertion, mybatis sets client id in clientVo

    return "redirect:/client/signup-success/" + clientId;
  }


  @RequestMapping("/signup-success/{id}")
  public String signupsuccess( @PathVariable("id") int clientId, Model model ) {

    ClientVo clientVo = clientService.getClientVoById(clientId);

    if (clientVo != null) {
      model.addAttribute( "clientVo", clientVo );
      return "client/signup-success";
    } else {
      return "client/signup-fail";
    }

  }


  @RequestMapping("/signinform")
  public String signinform() {
    return "client/signinform";
  }


  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  public String signin(@ModelAttribute ClientVo clientVo, HttpServletRequest request, Model model) {

    clientVo = clientService.getClientVoByEmailPassword( clientVo );

    // authentication fail
    if(clientVo == null) {
      model.addAttribute("auth", false);
      return "client/signinform";
    }

    //authentication success!
    //create session and set 'authClient' variable on session context
    HttpSession session = request.getSession(true);
    session.setAttribute("authClient", clientVo);

    return "redirect:/";
  }


  @RequestMapping("/signout")
  public String signout( HttpServletRequest request ) {

    HttpSession session = request.getSession();

    if( session != null){
      session.removeAttribute( "authUser" );
      session.invalidate();
    }
    return "redirect:/";
  }
}
