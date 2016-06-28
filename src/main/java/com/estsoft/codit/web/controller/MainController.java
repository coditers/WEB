package com.estsoft.codit.web.controller;

import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
  public String makeRecruit(){


    return "";
  }

  @RequestMapping("/viewrecruit")
  public String viewRecruit(){

    // 1. expired recruit
    // 2. creating recruit
    // 3. on-going recruit

    return "";
  }

}