package com.estsoft.codit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by JisungLim on 2016-06-20.
 */
@Controller
@RequestMapping("/main")
public class MainController {

  //main
  @RequestMapping("/")
  public String index() {
<<<<<<< HEAD
    return "main/index";
=======
    return "recruit/ready/recruit-ready-probselectform";
>>>>>>> a09867516613cb074411cb07f671562904dd2edd
  }
}


