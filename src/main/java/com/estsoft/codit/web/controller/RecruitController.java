package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @RequestMapping("/")
  public String main(@PathVariable("id") int id) {
    //
    RecruitVo recruitVo = recruitService.getVo(id);

    /* TODO - specify whether the time is before or after the starting point.
      IF ( before the starting time )
        return recruit/ready/recruit-ready-appregform.jsp;

      ELSE IF ( after the starting time )
        return recruit/started/recruit-started-appstatform.jsp;
     */

    return null;

  }

  @RequestMapping("/appl")
  public String applicantform() {

    /* TODO - specify whether the time is before or after the starting point.
      IF ( before the starting time )
        ApplicantRegisterService();
        return recruit/ready/recruit-ready-probselectform.jsp;

      ELSE IF ( after the starting time )
        ApplicantStatService();
        return recruit/started/recruit-started-probstatform.jsp;
     */

    return null;
  }

  @RequestMapping("/ajax-appreg")
  public String applicantregister() {

  }

  @RequestMapping("/ajax-appstat")
  public String applicantstat() {

  }

  @RequestMapping("/prob")
  public String problemform() {
    /* TODO - specify whether the time is before or after the starting point.
      IF ( before the starting time )
        ProblemSelectService();
        return recruit/ready/recruit-ready-probselectform.jsp;

      ELSE IF ( after the starting time )
        ProblemStatService();
        return recruit/started/recruit-started-probstatform.jsp;
     */

    return null;
  }

  @RequestMapping("/ajax-probselec")
  public String problemselect() {

  }

  @RequestMapping("/ajax-probstat")
  public String problemstat() {

  }

  @RequestMapping("/ajax-overallreport")
  public

}
