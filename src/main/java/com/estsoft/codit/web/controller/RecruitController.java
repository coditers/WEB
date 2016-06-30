package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/recruit/{recruitId}")
public class RecruitController {


  @Autowired
  RecruitService recruitService;

  // Todo non-auth user access deny
  @RequestMapping("main")
  public String main(@PathVariable("recruitId") int id, Model model) {

    // Valid check
    RecruitVo recruitVo = recruitService.getVo(id);
    if (recruitVo == null) {
      return "redirect:/main";
    }

    model.addAttribute("recruitId", id);
    //get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String current_date = dayTime.format(new Date(time));


    if (recruitVo.getFrom_date() == null || current_date.compareTo(recruitVo.getFrom_date()) < 0) {   //ready recruit

      return "recruit/ready/recruit-ready-main";

    } else if (current_date.compareTo(recruitVo.getFrom_date()) > 0) {// expired or on-going recruit

      if (current_date.compareTo(recruitVo.getTo_date()) > 0) {//expired recruit
        //enable expired flag
      }
      // 3. on-going recruit
      return "recruit/started/recruit-started-main";
    } else {
      return "redirect:/main";
    }
  }

  // MODULE - READY - APPLICANT REGISTER
  @RequestMapping("/appregform")
  public String applicantregisterform(@PathVariable("recruitId") int id, Model model) {
//    //Auth
//    if ( !authRecruit(id, session) ) {
//      return "redirect:/main/";
//    }

    model.addAttribute("recruitVo", recruitService.getVo(id));

    return "recruit/ready/recruit-ready-appregform";
  }

  /**
   * get excel file and save parsed data as applicant into DB
   * */
  @RequestMapping("/appreg")
  public String applicantregister(@PathVariable("recruitId") int id, @RequestParam("excel-file") MultipartFile file, Model model) {

    String filePath = recruitService.saveMultiPartFile(file);
    List<ApplicantVo> list = null;

    if(filePath != null) {
      list = recruitService.parseExcel(filePath, id);

      if(list != null) recruitService.insertApplicantList(list);

      model.addAttribute("applicantList", list);
    }
    model.addAttribute("recruitVo", recruitService.getVo(id));
    return "recruit/ready/recruit-ready-appregform";
  }

  @RequestMapping("set-recruit-date")
  public String setRecruitDate(){
    return null;
  }

  @RequestMapping("send-tickets")
  public String sendTickets(){
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
