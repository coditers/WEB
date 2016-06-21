package com.estsoft.codit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by JisungLim on 2016-06-20.
 */
@Controller
@RequestMapping("/main")
public class MainController {

  @RequestMapping("/")
  public String index() {
    return "main/index";
  }
}
