package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.CartVo;
import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.estsoft.codit.web.service.RecruitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/recruit/{recruitId}")
public class RecruitController {

  @Autowired
  RecruitService recruitService;

  @RequestMapping("main")
  public String main(@PathVariable("recruitId") int id, Model model) {

    RecruitVo recruitVo = recruitService.getRecruitVo(id);

    //get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String current_date = dayTime.format(new Date(time));
    model.addAttribute("recruitVo", recruitVo);

    System.out.println("RecruitController 42: "+recruitVo);

    if (recruitVo.getFromDate() == null || current_date.compareTo(recruitVo.getFromDate()) < 0) {   //ready recruit

      return "recruit/ready/recruit-ready-main";

    } else if (current_date.compareTo(recruitVo.getFromDate()) > 0) {// expired or on-going recruit

      if (current_date.compareTo(recruitVo.getToDate()) > 0) {//expired recruit
        //enable expired flag
      }
      // 3. on-going recruit
      return "recruit/started/recruit-started-main";
    } else {
      return "redirect:/";
    }
  }

  @RequestMapping("/appregform")
  public String applicantregisterform(@PathVariable("recruitId") int id, Model model) {
    model.addAttribute("recruitVo", recruitService.getRecruitVo(id));
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

      if(list != null)
        recruitService.insertApplicantList(list);

      model.addAttribute("applicantList", list);
    }
    model.addAttribute("recruitVo", recruitService.getRecruitVo(id));
    return "recruit/ready/recruit-ready-appregform";
  }

  @RequestMapping("setrecruitdate")
  public String setRecruitDate(){
    return null;
  }

  /**
   * todo ajax 처리
   * */
  @RequestMapping("sendticket")
//  @ResponseBody
  public Object sendTickets(@PathVariable("recruitId") int id){

//    boolean applicantFlag = recruitService.isApplicantRegistered( id );
//    boolean dateSetFlag = recruitService.isRecruitDateSet( id );
//    boolean probSelectFlag = recruitService.isProblemSelected( id );
//
//
//    Map<String, Object> map = new HashMap<String, Object>();
//    map.put("applicantFlag", applicantFlag);
//    map.put("dateSetFlag", dateSetFlag);
//    map.put("probSelectFlag", probSelectFlag);
//
//    if(applicantFlag == true && dateSetFlag == true && probSelectFlag == true)
      recruitService.sendTickets(id);
//    return map;
    return "main/index";
  }

  @RequestMapping("probselectform")
  public String problemSelectForm( @PathVariable("recruitId") int id, Model model){

    //view prob info
    List<ProblemInfoVo> problemInfoVoList = recruitService.getProblemInfoVoList();

    //todo cart list view

    model.addAttribute("problemInfoVoList", problemInfoVoList);
    model.addAttribute("recruitId", id);

    return "recruit/ready/recruit-ready-probselectform";
  }


  @RequestMapping("/selectproblem")
  public String selectProblem(@PathVariable("recruitId") int id , @RequestParam(value= "probIdList") int [] probIdList){

    //todo if carts are already stored delete carts.

    for ( int i : probIdList){
      System.out.println(i);
      CartVo vo = new CartVo();
      vo.setProblemInfoId( i );
      vo.setRecruitId(id);
      recruitService.insertCart( vo );
    }
    return "recruit/ready/recruit-ready-main";

  }

  @RequestMapping("/applicantstatform")
  public String applicantStatForm() {
    //어려우니 나중으로
    return "recruit/started/recruit-started-appstatform";
  }

  @RequestMapping("/problemstatform")
  public String problemStatForm() {
    return "recruit/started/recruit-started-probstatform";
  }

  @RequestMapping("/overallreportform")
  public String overallReportForm() {
    return "recruit/started/recruit-started-overallreportform";
  }
}
