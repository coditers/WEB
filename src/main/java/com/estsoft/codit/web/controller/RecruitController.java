package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.*;
import com.estsoft.codit.web.service.RecruitService;

import com.estsoft.codit.db.vo.ApplicantStatVo;
import com.estsoft.codit.db.vo.ProblemStatVo;
import com.estsoft.codit.web.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/recruit/{recruitId}")
public class RecruitController {

  @Autowired
  RecruitService recruitService;


  @RequestMapping("main")
  public String main(@PathVariable("recruitId") int recruitId, Model model) {

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);

    //Invalid access
    if(recruitVo == null)
      return "redirect:/";

    //Get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String current_date = dayTime.format(new Date(time));
    model.addAttribute("recruitVo", recruitVo);
    model.addAttribute("applicantCount", recruitService.getApplicantCountByRecruitId(recruitId));
    model.addAttribute("problemCount", recruitService.getProblemCountByRecruitId(recruitId));

    //before test
    if (recruitVo.getFromDate() == null || current_date.compareTo(recruitVo.getFromDate()) < 0) {

      return "recruit/ready/recruit-ready-main";

    // test started
    } else if (current_date.compareTo(recruitVo.getFromDate()) > 0) {
      if (current_date.compareTo(recruitVo.getToDate()) > 0) {

      }
      model.addAttribute("submittedApplicantCount", recruitService.getSubmittedApplicantCountByRecruitId(recruitId));
      return "recruit/started/recruit-started-main";

  } else {
      return "redirect:/";
    }
  }

  @RequestMapping("/appregform")
  public String applicantRegisterForm(@PathVariable("recruitId") int recruitId, Model model) {

    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    model.addAttribute("applicantList", recruitService.getApplicantListByRecruitId(recruitId));
    return "recruit/ready/recruit-ready-appregform";
  }


  @RequestMapping("/register-applicant")
  @ResponseBody
  public Map<String, Object> registerApplicant(@PathVariable("recruitId") int recruitId, MultipartHttpServletRequest request) {

    Iterator<String> itr = request.getFileNames();
    MultipartFile mpf = request.getFile(itr.next());

    String filePath = Util.saveMultiPartFile(mpf);

    List<ApplicantVo> list = null;
    Map<String, Object> map = new HashMap<String, Object>();


    //multipart file save fail
    if(filePath == null) {
      //todo save multipartfile fail error handle

    }else{
      list = recruitService.fetchApplicantListFromExcel(filePath, recruitId);

      if (list != null)
        recruitService.enrollApplicantList(list, recruitId);

      map.put("result", "success");
      map.put("data", list);
    }
    return map;
  }

  @RequestMapping(value = "set-recruitdate", method= RequestMethod.POST)
  public String setRecruitDate(@PathVariable("recruitId") int recruitId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate){

    //get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDate = dayTime.format(new Date(time));

    //invalid DateSetting
    if(fromDate.compareTo(toDate) > 0 || fromDate.compareTo(currentDate) < 0)
      return "redirect:main";

    recruitService.setRecruitDate(recruitId, fromDate, toDate);
    return "redirect:main";
  }

  /**
   * todo ajax 처리
   * */
  @RequestMapping("send-ticket")
//  @ResponseBody
  public String sendTickets(@PathVariable("recruitId") int recruitId, Model model){

    boolean applicantFlag = recruitService.isApplicantRegistered( recruitId );
    boolean dateSetFlag = recruitService.isRecruitDateSet( recruitId );
    boolean probSelectFlag = recruitService.isProblemSelected( recruitId );

//    model.addAttribute("applicantFlag", applicantFlag);
//    model.addAttribute("dateSetFlag", dateSetFlag);
//    model.addAttribute("probSelectFlag", probSelectFlag);

//    Map<String, Object> map = new HashMap<String, Object>();
//    map.put("applicantFlag", applicantFlag);
//    map.put("dateSetFlag", dateSetFlag);
//    map.put("probSelectFlag", probSelectFlag);

    if(applicantFlag == true && dateSetFlag == true && probSelectFlag == true)
      recruitService.sendTickets(recruitId);
//    return map;
    return "redirect:main";
  }

  @RequestMapping("problem-selectform")
  public String problemSelectForm( @PathVariable("recruitId") int recruitId, Model model){

    //view prob info
    List<ProblemInfoVo> problemInfoVoList = recruitService.getAllProblemInfoList();

    //todo cart list view
    if(recruitService.isProblemSelected(recruitId))
    model.addAttribute("selectedProblemList", recruitService.getProblemInfoListByRecruitId(recruitId));

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);
    model.addAttribute("recruitVo", recruitVo);
    model.addAttribute("problemInfoVoList", problemInfoVoList);
    //model.addAttribute("cartList", recruitService.getCartListByRecruitId(recruitId));

    return "recruit/ready/recruit-ready-probselectform";
  }


  @RequestMapping("/select-problem")
  public String selectProblem(@PathVariable("recruitId") int recruitId , Model model,  @RequestParam(value= "probIdList") int [] probIdList){

    recruitService.insertCartList(recruitId, probIdList);

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);
    model.addAttribute("recruitVo", recruitVo);
    return "redirect:main";
  }

  //// TODO: 2016-07-20 emailform으로 들어가는 method를 짰습니다

  @RequestMapping("/write-emailform")
  public String writeEmailForm(@PathVariable("recruitId") int recruitId , Model model) {
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/ready/recruit-ready-writeemailform";
  }

  @RequestMapping("/save-email")
  public String saveEmail(@PathVariable("recruitId") int recruitId , @RequestParam("emailFormat") String emailFormat) {
    recruitService.saveEmailFormat(recruitId, emailFormat);
    return "redirect:main";
  }

  @RequestMapping("/applicantstatform")
  public String applicantStatForm(@PathVariable("recruitId") int recruitId , Model model) {

    List<ApplicantStatVo> applicantStatVoList = recruitService.getApplicantStatList(recruitId);
    List<ProblemInfoVo> problemInfoVoList = recruitService.getProblemInfoListByRecruitId(recruitId);

    model.addAttribute("applicantStatList", applicantStatVoList);
    model.addAttribute("problemInfoList", problemInfoVoList);
    model.addAttribute("recruitId", recruitId);
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/started/recruit-started-appstatform";
  }


  @RequestMapping("/ajax-applicantresultdetail")
  @ResponseBody
  public Object applicantResultDetail(@PathVariable("recruitId") int recruitId,
                                @RequestParam("applicantId") int applicantId,
                                @RequestParam("problemInfoId") int problemInfoId) {
    //todo 통과율
    List<ResultVo> resultList= recruitService.getResultList(applicantId, problemInfoId);
    System.out.println(resultList);
    return resultList;
  }

  @RequestMapping("/problemstatform")
  public String problemStatForm(@PathVariable("recruitId") int recruitId , Model model) {
    List<ProblemStatVo> problemStatVoList = recruitService.getProblemStatList(recruitId);
    model.addAttribute("problemStatList", problemStatVoList);
    model.addAttribute("recruitId", recruitId);
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/started/recruit-started-probstatform";
  }
}
