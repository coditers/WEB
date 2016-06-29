package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main")
public class MainController {

  @Autowired
  RecruitService recruitService;

  //main
  @RequestMapping("")
  public String index() {

    // 1. check logged in or not -> view the table or not -> job for .jsp

    return "main/index";
  }

  @RequestMapping("/makerecruitform")
  public String makeRecruitForm(){

    //get recruit name

    return "";
  }

  @RequestMapping("/makerecruit")
  public String makeRecruit(HttpServletRequest request){ // @ModelAttribute RecruitVo recruitVo

    int id = ((ClientVo)(request.getSession().getAttribute("authClient"))).getId();
    System.out.println( (ClientVo)(request.getSession().getAttribute("authClient")) );
    /*=========temporal code=========*/
    RecruitVo recruitVo = new RecruitVo();
    recruitVo.setClient_id(id);
    recruitVo.setTitle("2016 last semester");
    /*===============================*/
    recruitService.insert(recruitVo);

    return "redirect:/recruit/"+ recruitVo.getId() + "/main";
  }


}