package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.*;
import com.estsoft.codit.db.vo.ApplicantStatVo;
import com.estsoft.codit.db.vo.ProblemStatVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;


@Service
public class RecruitService {

    @Autowired
    RecruitRepository recruitRepository;
    @Autowired
    ApplicantRepository applicantRepository;
    @Autowired
    ProblemInfoRepository problemInfoRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ResultRepository resultRepository;


    //Service associated with RecruitRepository
    public int              insertRecruitVo(RecruitVo recruitVo) { return recruitRepository.insert(recruitVo); }
    public RecruitVo        getRecruitVo(int id) { return recruitRepository.getById(id); }
    public List<RecruitVo>  getRecruitListByClientId(int clientId) { return recruitRepository.getListByClientId(clientId); }
    public void             setRecruitDate(int recruitId, String fromDate, String toDate) { recruitRepository.updateRecruitDate(recruitId, fromDate, toDate); }
    public void             saveEmailFormat(int recruitId, String emailFormat) { recruitRepository.updateEmailFormat(recruitId, emailFormat); }


    public List<ProblemInfoVo> getAllProblemInfoList() { return problemInfoRepository.getList(); }
    public List<ProblemInfoVo> getProblemInfoListByRecruitId( int recruitId ) { return problemInfoRepository.getListByRecruitId(recruitId); }


    public void insertApplicantList(List<ApplicantVo> list) {
        applicantRepository.insertList(list);
    }
    public void insertCart(CartVo vo) {
        cartRepository.insert(vo);
    }
    public List<ResultVo> getResultList(int applicantId, int problemInfoId) { return resultRepository.getResultList(applicantId, problemInfoId); }


    /**
     * Calculate applicantStatVo and store vos into arraylist
     * */
    public List<ApplicantStatVo> getApplicantStatList(int recruitId) {

        List<ApplicantStatVo> applicantStatVoList = new ArrayList<ApplicantStatVo>();

        //Get Applicant List and cart list
        List<ApplicantVo> applicantVoList = applicantRepository.getListByRecruitId(recruitId);
        List<CartVo> cartVoList = cartRepository.getListByRecruitId(recruitId);

        //Calculate scores for each problem of an applicant
        for (ApplicantVo applicantVo : applicantVoList) {
            ApplicantStatVo applicantStatVo = new ApplicantStatVo();
            for (CartVo cartVo : cartVoList) {
                List<ResultVo> resultList = resultRepository.getResultList(applicantVo.getId(), cartVo.getProblemInfoId());
                int correctCnt = 0;
                int totalCnt = 0;
                for (ResultVo resultVo : resultList) {

                    if (resultVo.isCorrectness() == true)
                        correctCnt++;

                    totalCnt++;
                }
                int problemScore = (totalCnt == 0) ? 0 : correctCnt * 100 / totalCnt;
                applicantStatVo.addScore(problemScore);
            }

            //Set applicantStatVo
            applicantStatVo.setApplicantId(applicantVo.getId());
            applicantStatVo.setApplicantName(applicantVo.getName());
            applicantStatVo.calTotalScore();

            //add applicantStatVo into list
            applicantStatVoList.add(applicantStatVo);
        }
        return applicantStatVoList;
    }
    /**
     * Calculate problemStatVo and store vos into arraylist
     * */
    public List<ProblemStatVo> getProblemStatList(int recruitId) {

        List<ProblemStatVo> problemStatVoList = new ArrayList<ProblemStatVo>();
        List<ApplicantStatVo> applicantStatVoList = getApplicantStatList(recruitId);
        List<ProblemInfoVo> problemList = problemInfoRepository.getListByRecruitId(recruitId);

        for (int i =0 ; i < problemList.size(); i++) {

            ProblemStatVo problemStatVo = new ProblemStatVo();

            int avg = 0;
            int stdDev = 0;
            int min = 999;
            int max = -1;
            ProblemInfoVo ithProblem = problemList.get(i);

            //calculate problem score sum and squared sum
            //store into avg and stdDev
            //get min max score in each problem
            for (ApplicantStatVo applicantStatVo : applicantStatVoList) {
                int ithProblemScore = applicantStatVo.getProblemScoreList().get(i);
                avg += ithProblemScore;
                stdDev += (ithProblemScore * ithProblemScore);
                if (min > ithProblemScore)
                    min = ithProblemScore;
                if (max < ithProblemScore)
                    max = ithProblemScore;
            }

            avg /= applicantStatVoList.size();

            //standard deviation fomula : squared sum average - squared average
            stdDev /= applicantStatVoList.size();
            stdDev -= (avg * avg);

            //Set problemStatVo
            problemStatVo.setProblemName(ithProblem.getName());
            problemStatVo.setAvg(avg);
            problemStatVo.setStdDev(stdDev);
            problemStatVo.setMax(max);
            problemStatVo.setMin(min);

            //add problemStatVo into list
            problemStatVoList.add(problemStatVo);
        }
        return problemStatVoList;
    }


    public boolean isApplicantRegistered(int recruitId) {
        return applicantRepository.getListByRecruitId(recruitId).isEmpty() == false;
    }
    public boolean isRecruitDateSet(int recruitId) {

        RecruitVo vo = recruitRepository.getById(recruitId);

        if ("".equals(vo.getFromDate()) || vo.getFromDate() == null)
            return false;

        return true;
    }
    public boolean isProblemSelected(int recruitId) {
        return cartRepository.getListByRecruitId(recruitId).isEmpty() == false;
    }


    /**
     * @Usage RecruitAuthInterceptor
     * check whether the recruit id belong to client or not*/
    public boolean isContained(int recruitId, int clientId) {

        RecruitVo vo = recruitRepository.getByIdClientId(recruitId, clientId);
        if (vo != null)
            return true;

        return false;
    }



    // TODO: 2016-06-29 Invalid Excel format handle
    public List<ApplicantVo> fetchApplicantListFromExcel(String filePath, int recruitId) {

        FileInputStream fis = null;
        String extName = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());

        List<ApplicantVo> list = new ArrayList<ApplicantVo>();

        try {

            //Read Excel file
            fis = new FileInputStream(filePath);
            Workbook workbook = null;

            //check excel format
            if ("xlsx".equals(extName)) {
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new HSSFWorkbook(fis);
            }

            //Excel parsing
            int rowindex = 0;
            int colindex = 0;
            Sheet sheet = workbook.getSheetAt(0);
            int nRow = sheet.getPhysicalNumberOfRows();

            for (rowindex = 1; rowindex < nRow; rowindex++) {
                ApplicantVo applicantVo = new ApplicantVo();
                Row row = sheet.getRow(rowindex);
                if (row != null) {
                    int nCell = row.getPhysicalNumberOfCells();
                    for (colindex = 0; colindex <= nCell; colindex++) {
                        Cell cell = row.getCell(colindex);
                        String value = "";
                        if (cell == null) {
                            continue;
                        } else {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_FORMULA:
                                    value = cell.getCellFormula();
                                    break;
                                case Cell.CELL_TYPE_NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    value = cell.getErrorCellValue() + "";
                                    break;
                            }
                        }
                        if (colindex == 0) {
                            applicantVo.setName(value);
                        } else { //assume column index 1 is email.
                            applicantVo.setEmail(value);
                        }
                    }
                    applicantVo.setRecruitId(recruitId);
                    applicantVo.setTicket(applicantVo.getEmail()); //// TODO: 2016-06-29 any good method?

                    //add applicantVo into list
                    list.add(applicantVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * send tickets to applicants belong to recruit.
     **/
    public void sendTickets(int recruitId) {

        //Get applicant list to send mail
        List<ApplicantVo> applicantList = applicantRepository.getListByRecruitId(recruitId);

        //Get EmailFormat
        RecruitVo recruitVo = recruitRepository.getById(recruitId);
        String emailFormat = recruitVo.getEmailFormat();

        //Customize email format and send mail.
        for (ApplicantVo vo : applicantList) {
            String emailContent = null;
            emailContent = emailFormat.replace("#이름", vo.getName());
            emailContent = emailContent.replace("#링크", "http://222.239.250.207:8888/?ticket=" + vo.getTicket() );
            //todo mail send fail log or handle
            sendMail(vo.getEmail(), vo.getName(), emailContent);
        }
    }

    private boolean sendMail(String toAddr, String applicantName, String content) {// String companyName

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "codit.com");

        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@codit.com", "codit"));
//      InternetAddress[] address = {new InternetAddress(toAddr)};
//      msg.setRecipients(Message.RecipientType.TO, address);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr, applicantName));
            msg.setSubject("invitation from codit");
//      MimeMultipart multipart = new MimeMultipart();
//
//      MimeBodyPart part = new MimeBodyPart();
//      part.setContent( "<a href>"+ content + "</a>", "text/html; charset=utf-8");
//      multipart.addBodyPart(part);

//      part = new MimeBodyPart();
//      FileDataSource fds = new FileDataSource("파일 경로");
//      part.setDataHandler(new DataHandler(fds));
//      part.setFileName("파일명");
//      multipart.addBodyPart(part);

//      msg.setContent(multipart);
            msg.setContent(content, "text/html; charset=utf-8");

            Transport.send(msg);
        } catch( UnsupportedEncodingException ex){
            ex.printStackTrace();
            return false;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    //아래 5 개 메소드 추가하였습니다 (7/27)
    public int getApplicantCountByRecruitId(int recruitId) {
        return applicantRepository.getCountByRecruitId(recruitId);

    }

    public int getSubmittedApplicantCountByRecruitId(int recruitId) {
        return applicantRepository.getSubmittedCountByRecruitId(recruitId);
    }

    public int getProblemCountByRecruitId(int recruitId) {
        return cartRepository.getCountByRecruitId(recruitId);

    }

    public List<ApplicantVo> getApplicantListByRecruitId(int recruitId){
        return applicantRepository.getListByRecruitId(recruitId);
    }

    public List<CartVo> getCartListByRecruitId(int recruitId){
        return cartRepository.getListByRecruitId(recruitId);
    }



}