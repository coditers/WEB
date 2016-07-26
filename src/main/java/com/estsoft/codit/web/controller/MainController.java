package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.Annotation.Auth;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class MainController {

  @Autowired
  RecruitService recruitService;

  /**
   * Forward Main Page
   * */
  @RequestMapping("")
  public String index(HttpServletRequest request, Model model) {

    ClientVo authClientVo = (ClientVo)(request.getSession().getAttribute("authClient"));

    //logged in
    if( authClientVo != null ){

      List<RecruitVo> recruitVoList = recruitService.getRecruitListByClientId( authClientVo.getId() );
      model.addAttribute("recruitVoList", recruitVoList);

      return "main/index_login";
    }// logged out
    else
      return "main/index";

  }

  /**
   * @param recruitVo  only has title value.
   */
  @Auth
  @RequestMapping(value = "/create-recruit")
  public String CreateRecruit(HttpServletRequest request, @ModelAttribute RecruitVo recruitVo){

    //Get Client ID
    int authClientId = ((ClientVo)(request.getSession().getAttribute("authClient"))).getId();

    //set client and insert into DB
    recruitVo.setClientId( authClientId );
    recruitService.insertRecruitVo( recruitVo );

    return "redirect:/recruit/"+ recruitVo.getId() + "/main";
  }
}
