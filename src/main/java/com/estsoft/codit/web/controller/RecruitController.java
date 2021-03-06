package com.estsoft.codit.web.controller;

import com.estsoft.codit.db.vo.*;
import com.estsoft.codit.web.service.RecruitService;

import com.estsoft.codit.db.vo.ApplicantStatVo;
import com.estsoft.codit.db.vo.ProblemStatVo;
import com.estsoft.codit.web.util.JasperReportUtil;
import com.estsoft.codit.web.util.MultipartFileUtil;


import org.apache.commons.dbcp.BasicDataSource;
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
import org.springframework.web.servlet.ModelAndView;

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

  @Autowired
  BasicDataSource dataSource;

   @RequestMapping("")
  public String main(@PathVariable("recruitId") int recruitId, Model model) {

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);

    //Invalid access
    if (recruitVo == null)
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

      return "recruit/ready/main";

      // test started
    } else if (current_date.compareTo(recruitVo.getFromDate()) > 0) {
      if (current_date.compareTo(recruitVo.getToDate()) > 0) {

      }
      model.addAttribute("submittedApplicantCount", recruitService.getSubmittedApplicantCountByRecruitId(recruitId));
      return "recruit/started/main";

    } else {
      return "redirect:/";
    }
  }

  @RequestMapping("/appregform")
  public String applicantRegisterForm(@PathVariable("recruitId") int recruitId, Model model) {

    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    model.addAttribute("applicantList", recruitService.getApplicantListByRecruitId(recruitId));
    return "recruit/ready/app-registration";
  }


  @RequestMapping("/register-applicant")
  @ResponseBody
  public Map<String, Object> registerApplicant(@PathVariable("recruitId") int recruitId, MultipartHttpServletRequest request) {

    Iterator<String> itr = request.getFileNames();
    MultipartFile mpf = request.getFile(itr.next());

    String filePath = MultipartFileUtil.saveMultiPartFile(mpf);

    List<ApplicantVo> list = null;
    Map<String, Object> map = new HashMap<String, Object>();


    //multipart file save fail
    if (filePath == null) {
      System.out.println("[Error] MultipartFile save fail");
    } else {
      list = recruitService.fetchApplicantListFromExcel(filePath, recruitId);

      if (list != null)
        recruitService.enrollApplicantList(list, recruitId);

      map.put("result", "success");
      map.put("data", list);
    }
    return map;
  }

  @RequestMapping(value = "set-recruitdate", method = RequestMethod.POST)
  public String setRecruitDate(@PathVariable("recruitId") int recruitId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {

    //get server time
    long time = System.currentTimeMillis();
    SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDate = dayTime.format(new Date(time));

    //invalid DateSetting
    if (fromDate.compareTo(toDate) > 0 || fromDate.compareTo(currentDate) < 0)
      return "redirect:/recruit/" + recruitId;

    recruitService.setRecruitDate(recruitId, fromDate, toDate);
    return "redirect:/recruit/" + recruitId;
  }

  /**
   *
   */
  @RequestMapping("send-ticket")
  public String sendTickets(@PathVariable("recruitId") int recruitId, Model model) {

    boolean applicantFlag = recruitService.isApplicantRegistered(recruitId);
    boolean dateSetFlag = recruitService.isRecruitDateSet(recruitId);
    boolean probSelectFlag = recruitService.isProblemSelected(recruitId);

    List<ApplicantVo> mailSendFailList = null;
    if (applicantFlag == true && dateSetFlag == true && probSelectFlag == true)
      mailSendFailList = recruitService.sendTickets(recruitId);

    model.addAttribute("mailSendFailList", mailSendFailList);

    return "redirect:/recruit/" + recruitId;
  }

  @RequestMapping("problem-selectform")
  public String problemSelectForm(@PathVariable("recruitId") int recruitId, Model model) {

    //view prob info
    List<ProblemInfoVo> problemInfoVoList = recruitService.getAllProblemInfoList();

    if (recruitService.isProblemSelected(recruitId))
      model.addAttribute("selectedProblemList", recruitService.getProblemInfoListByRecruitId(recruitId));

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);
    model.addAttribute("recruitVo", recruitVo);
    model.addAttribute("problemInfoVoList", problemInfoVoList);
    model.addAttribute("cartList", recruitService.getCartListByRecruitId(recruitId));

    return "recruit/ready/select-problem";
  }


  @RequestMapping("/select-problem")
  public String selectProblem(@PathVariable("recruitId") int recruitId, Model model, @RequestParam(value = "probIdList") int[] probIdList) {

    recruitService.insertCartList(recruitId, probIdList);

    RecruitVo recruitVo = recruitService.getRecruitVo(recruitId);
    model.addAttribute("recruitVo", recruitVo);
    return "redirect:/recruit/" + recruitId;
  }

  //// TODO: 2016-07-20 emailform으로 들어가는 method를 짰습니다

  @RequestMapping("/write-emailform")
  public String writeEmailForm(@PathVariable("recruitId") int recruitId, Model model) {
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/ready/write-email";
  }

  @RequestMapping("/save-email")
  public String saveEmail(@PathVariable("recruitId") int recruitId, @RequestParam("emailFormat") String emailFormat) {
    recruitService.saveEmailFormat(recruitId, emailFormat);
    return "redirect:/recruit/" + recruitId;
  }

  @RequestMapping("/applicantstatform")
  public String applicantStatForm(@PathVariable("recruitId") int recruitId, Model model) {

    List<ApplicantStatVo> applicantStatVoList = recruitService.getApplicantStatList(recruitId);
    List<ProblemInfoVo> problemInfoVoList = recruitService.getProblemInfoListByRecruitId(recruitId);

    model.addAttribute("applicantStatList", applicantStatVoList);
    model.addAttribute("problemInfoList", problemInfoVoList);
    model.addAttribute("recruitId", recruitId);
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/started/app-stat";
  }


  @RequestMapping("/applicantresultdetail")
  public ModelAndView applicantResultDetail(@PathVariable("recruitId") int recruitId,
                                      @RequestParam("applicantId") int applicantId) {

    return new ModelAndView("multiformat-view", JasperReportUtil.render(recruitId, applicantId));
  }

  @RequestMapping("/problemstatform")
  public String problemStatForm(@PathVariable("recruitId") int recruitId, Model model) {
    List<ProblemStatVo> problemStatVoList = recruitService.getProblemStatList(recruitId);
    model.addAttribute("problemStatList", problemStatVoList);
    model.addAttribute("recruitId", recruitId);
    model.addAttribute("recruitVo", recruitService.getRecruitVo(recruitId));
    return "recruit/started/problem-stat";
  }

  @RequestMapping(value = "/add-problem", method = RequestMethod.GET)
  public String addProblemForm(Model model, @PathVariable("recruitId") int recruitId) {
    model.addAttribute("recruitId", recruitId);
    return "recruit/ready/add-problem";
  }

  @RequestMapping(value="/add-problem", method=RequestMethod.POST)
  public String addProblem(
          @PathVariable("recruitId") int recruitId,
          @ModelAttribute ProblemInfoVo problemInfoVo,
          @RequestParam("skeleton-code-c") String skeletonCodeC,
          @RequestParam("skeleton-code-java") String skeletonCodeJava,
          @RequestParam("skeleton-code-python") String skeletonCodePython) {

    String[] skeletonCodeList = {skeletonCodeC, skeletonCodeJava, skeletonCodePython};
    recruitService.addProblem(problemInfoVo, skeletonCodeList);

    return "redirect:/recruit/"+recruitId+"/problem-selectform";
  }
}
