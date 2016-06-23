package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ClientVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * URI : localhost:8080/web/recruit/{id}/~.
 * <p/>
 * Created by JisungLim on 2016-06-20.
 */
@Controller
@RequestMapping("/recruit/{id}")
public class RecruitController {

  @Autowired
  RecruitService recruitService;

  // MODULE - Authentication
  private boolean authRecruit(int id, HttpSession session) {

    // Get auth client from session
    ClientVo authClient = (ClientVo) session.getAttribute("authClient");

    // Authentication
    if(authClient == null) {
      return false;
    }

    // Get recruit VO by id from database.
    RecruitVo recruitVo = recruitService.getVo(id);

    //
    return (recruitVo.getClient_id() == authClient.getId());
  }

  // MODULE - MAIN DASHBOARD
  @RequestMapping("/")
  public String main(@PathVariable("id") int id,
                     HttpServletRequest request,
                     HttpSession session) {



    /* TODO - specify whether the time is before or after the starting point.
      IF ( before the starting time )
        return "recruit/ready/recruit-ready-main";

      ELSE IF ( after the starting time && before the end time )
        return "recruit/started/recruit-started-main";
     */

    return null;

  }

  // MODULE - READY - APPLICANT REGISTER
  @RequestMapping("/appreg")
  public String applicantregisterform(@PathVariable("id") int id,
                                      HttpSession session) {
    //Auth
    if ( !authRecruit(id, session) ) {
      return "redirect:/main/";
    }

    return "recruit/ready/recruit-ready-appregform";
  }

  @RequestMapping("/ajax-appreg")
  public String applicantregister() {
    // TODO - Application Register
    return null;
  }

  //// MODULE - READY - PROBLEM SELECTION
  @RequestMapping("/probselect")
  public String problemselectform(@PathVariable("id") int id,
                                  HttpSession session) {
    //Auth
    if ( !authRecruit(id, session) ) {
      return "redirect:/main/";
    }

    return "recruit/ready/recruit-ready-probselectform";
  }

  @RequestMapping("/ajax-probselec")
  public String problemselect() {
    // TODO - Problem Selection
    return null;
  }

  //// MODULE - STARTED - APPLICANT STATISTICS
  @RequestMapping("/appstat")
  public String applicantstatform(@PathVariable("id") int id,
                                  HttpSession session) {
    //Auth
    if ( !authRecruit(id, session) ) {
      return "redirect:/main/";
    }

    return "recruit/started/recruit-started-appstatform";
  }

  @RequestMapping("/ajax-appstat")
  public String applicantstat() {
    // TODO - Application Statistic
    return null;
  }

  //// MODULE - STARTED - PROBLEM STATISTICS
  @RequestMapping("/probstat")
  public String problemstatform(@PathVariable("id") int id,
                                HttpSession session) {
    //Auth
    if ( !authRecruit(id, session) ) {
      return "redirect:/main/";
    }

    return "recruit/started/recruit-started-probstatform";
  }

  @RequestMapping("/ajax-probstat")
  public String problemstat() {
    // TODO - Problem Statistic
    return null;
  }

  //// MODULE - STARTED - OVERALL REPORT
  @RequestMapping("/report")
  public String overallreportform(@PathVariable("id") int id,
                                  HttpSession session) {
    //Auth
    if ( !authRecruit(id, session) ) {
      return "redirect:/main/";
    }

    return "recruit/started/recruit-started-overallreportform";
  }

  @RequestMapping("/ajax-overallreport")
  public String overallreport() {
    // TODO - Overall Report
    return null;
  }
}
