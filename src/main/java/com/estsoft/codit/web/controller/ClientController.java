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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

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


  @RequestMapping("/checkemail")
  @ResponseBody
  public Map<String, Object> checkemail(@RequestParam("email") String email) {
    System.out.println("ClientController 42:"+email);
    ClientVo vo = clientService.getClientVoByEmail(email);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("result", "success");
    map.put("data", vo==null);
    return map;
  }

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

}
