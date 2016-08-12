package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.Annotation.Auth;
import com.estsoft.codit.web.Annotation.AuthClient;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public String index(@AuthClient ClientVo authClient, Model model) {
    //인증 안한 경우
    if(authClient==null){
      return "main/index";
    }
    List<RecruitVo> recruitVoList = recruitService.getRecruitListByClientId( authClient.getId() );
    model.addAttribute("recruitVoList", recruitVoList);
    return "main/index_login";
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
