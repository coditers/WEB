package com.estsoft.codit.web.controller;

import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by JisungLim on 2016-06-20.
 */
@Controller
@RequestMapping("/recruit")
public class RecruitController {

  @Autowired
  RecruitService recruitService;

//  @RequestMapping("/{id}")
//  public String main(@PathVariable("id") int id) {
//
//  }


}
