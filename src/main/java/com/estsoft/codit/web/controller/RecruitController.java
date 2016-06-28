package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/recruit")
public class RecruitController {

  @Autowired
  RecruitService recruitService;

  //@Auth
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

    return "redirect:/recruit/main/" + recruitVo.getId();
  }

  // Todo non-auth user access deny
  @RequestMapping("main/{recruitId}")
  public String main(@PathVariable("recruitId") int id,
                     HttpServletRequest request) {

    RecruitVo recruitVo = recruitService.getVo(id);
    if(recruitVo == null){
      return "redirect:/main";
    }

    //get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String current_date = dayTime.format(new Date(time));

    System.out.println("\n\n\n\n==============current time ============> " + current_date);
    //// TODO: 2016-06-28 need to synchronize java time format and mysql time format

    if( recruitVo.getFrom_date() == null || current_date.compareTo( recruitVo.getFrom_date() ) < 0 ) {   //ready recruit

      return "recruit/ready/recruit-ready-main";

    }else if( current_date.compareTo( recruitVo.getFrom_date() ) > 0 ) {// expired or on-going recruit

      if (current_date.compareTo(recruitVo.getTo_date()) > 0) {//expired recruit
        //enable expired flag
      }
      // 3. on-going recruit
      return "recruit/started/recruit-started-main";
    }else{
       return "redirect:/main";
    }
  }

  // MODULE - READY - APPLICANT REGISTER
//  @RequestMapping("/appregform")
//  public String applicantregisterform(@PathVariable("id") int id,
//                                      HttpSession session) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }
//
//    return "recruit/ready/recruit-ready-appregform";
//  }

  @RequestMapping("/ajax-appreg")
  public String applicantregister() {
    // TODO - Application Register
    return null;
  }

  //// MODULE - READY - PROBLEM SELECTION
//  @RequestMapping("/probselect")
//  public String problemselectform(@PathVariable("id") int id,
//                                  HttpSession session) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }
//
//    return "recruit/ready/recruit-ready-probselectform";
//  }

  @RequestMapping("/ajax-probselec")
  public String problemselect() {
    // TODO - Problem Selection
    return null;
  }

  //// MODULE - STARTED - APPLICANT STATISTICS
//  @RequestMapping("/appstat")
//  public String applicantstatform(@PathVariable("id") int id,
//                                  HttpSession session) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }
//
//    return "recruit/started/recruit-started-appstatform";
//  }

  @RequestMapping("/ajax-appstat")
  public String applicantstat() {
    // TODO - Application Statistic
    return null;
  }

  //// MODULE - STARTED - PROBLEM STATISTICS
//  @RequestMapping("/probstat")
//  public String problemstatform(@PathVariable("id") int id,
//                                HttpSession session) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }
//
//    return "recruit/started/recruit-started-probstatform";
//  }

  @RequestMapping("/ajax-probstat")
  public String problemstat() {
  // TODO - Problem Statistic
    return null;
  }

  //// MODULE - STARTED - OVERALL REPORT
//  @RequestMapping("/report")
//  public String overallreportform(@PathVariable("id") int id,
//                                  HttpSession session) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }
//
//    return "recruit/started/recruit-started-overallreportform";
//  }

  @RequestMapping("/ajax-overallreport")
  public String overallreport() {
    // TODO - Overall Report
    return null;
  }
}
