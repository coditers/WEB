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

  //main
  @RequestMapping("")
  public String index(HttpServletRequest request, Model model) {
    ClientVo vo = null;
    if(( vo = (ClientVo)(request.getSession().getAttribute("authClient"))) != null){
      List<RecruitVo> recruitList = recruitService.getRecruitListByClientId( vo.getId() );
      model.addAttribute("recruitList", recruitList);
      return "main/index_login";
    }
    else
      return "main/index";
  }

  @Auth
  @RequestMapping(value = "/makerecruit")//, method= RequestMethod.POST)
  public String makeRecruit(HttpServletRequest request){//@ModelAttribute RecruitVo recruitVo){

    int id = ((ClientVo)(request.getSession().getAttribute("authClient"))).getId();
    /*=========temporal code=========*/
    RecruitVo recruitVo = new RecruitVo();
    recruitVo.setClientId(id);
    recruitVo.setTitle("2016 last semester");
    /*===============================*/

    recruitVo.setClientId(id);
    recruitService.insert(recruitVo);

    return "redirect:/recruit/"+ recruitVo.getId() + "/main";
  }


}
