package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.*;
import com.estsoft.codit.web.util.ApplicantStatVo;
import com.estsoft.codit.web.util.ProblemStatVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    //  directory path in server environment
//  private static final String FILE_SAVE_PATH = "./multipartData";
    private static final String FILE_SAVE_PATH = "C:/temp/";

    public RecruitVo getRecruitVo(int id) {
        return recruitRepository.get(id);
    }

    public List<RecruitVo> getRecruitListByClientId(int clientId) {
        return recruitRepository.getListByClientId(clientId);
    }

    public List<ProblemInfoVo> getProblemInfoVoList() {
        return problemInfoRepository.getList();
    }

    public List<ProblemInfoVo> getProblemInfoList(int recruitId) {
        return problemInfoRepository.getProblemInfoList(recruitId);
    }

    public int insert(RecruitVo recruitVo) {
        return recruitRepository.insert(recruitVo);
    }

    public void insertApplicantList(List<ApplicantVo> list) {
        applicantRepository.insertList(list);
    }

    public void insertCart(CartVo vo) {
        cartRepository.insert(vo);
    }

    public List<ApplicantStatVo> getApplicantStatVoList(int recruitId) {

        List<ApplicantStatVo> applicantStatVoList = new ArrayList<ApplicantStatVo>();

        List<ApplicantVo> applicantVoList = applicantRepository.getListByRecruitId(recruitId);
        List<CartVo> cartVoList = cartRepository.getListByRecruitId(recruitId);

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
                applicantStatVo.addScore(problemScore); //score of an applicant for a problem
            }
            applicantStatVo.setApplicantId(applicantVo.getId());
            applicantStatVo.setApplicantName(applicantVo.getName());
            applicantStatVo.calTotalScore();
            applicantStatVoList.add(applicantStatVo);
        }
        return applicantStatVoList;
    }

    public List<ProblemStatVo> getProblemStatVoList(int recruitId) {

        List<ProblemStatVo> problemStatVoList = new ArrayList<ProblemStatVo>();
        List<ApplicantStatVo> applicantStatVoList = getApplicantStatVoList(recruitId);
        List<ProblemInfoVo> problemInfoVoList = problemInfoRepository.getProblemInfoList(recruitId);

        for (int i =0 ; i < problemInfoVoList.size(); i++) {

            ProblemStatVo problemStatVo = new ProblemStatVo();
            int avg = 0;
            int stdDev = 0;
            int min = 999;
            int max = -1;
            ProblemInfoVo problemInfoVo = problemInfoVoList.get(i);

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
            stdDev /= applicantStatVoList.size();
            stdDev -= (avg * avg);

            problemStatVo.setProblemName(problemInfoVo.getName());
            problemStatVo.setAvg(avg);
            problemStatVo.setStdDev(stdDev);
            problemStatVo.setMax(max);
            problemStatVo.setMin(min);
            problemStatVoList.add(problemStatVo);
        }
        return problemStatVoList;
    }

    public List<ResultVo> getResultList(int applicantId, int problemInfoId) {
        return resultRepository.getResultList(applicantId, problemInfoId);
    }

    public void setRecruitDate(int recruitId, String fromDate, String toDate) {
        recruitRepository.updateRecruitDate(recruitId, fromDate, toDate);
    }

    public boolean isApplicantRegistered(int recruitId) {
        return applicantRepository.getListByRecruitId(recruitId).isEmpty() == false;
    }

    public boolean isRecruitDateSet(int recruitId) {
        RecruitVo vo = recruitRepository.get(recruitId);

        if ("".equals(vo.getFromDate()) || vo.getFromDate() == null)
            return false;

        return true;
    }

    public boolean isProblemSelected(int recruitId) {
        return cartRepository.getListByRecruitId(recruitId).isEmpty() == false;
    }

    public void saveEmail(int recruitId, String emailFormat) {
        recruitRepository.updateEmailFormat(recruitId, emailFormat);
    }

    public boolean isContained(int recruitId, int clientId) {

        RecruitVo vo = recruitRepository.getByIdClientId(recruitId, clientId);
        if (vo != null)
            return true;

        return false;
    }

    /**
     * if success saving the multipart file,
     * return file path
     * else return null
     */
    public String saveMultiPartFile(MultipartFile file) {

        if (file.isEmpty() == false) {

            String fileOriginalName = file.getOriginalFilename();
            String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length());
            String saveFileName = genSaveFileName(extName);

            writeFile(file, FILE_SAVE_PATH, saveFileName);
            return FILE_SAVE_PATH + saveFileName;
        } else
            return null;
    }

    private void writeFile(MultipartFile file, String path, String fileName) {
        FileOutputStream fos = null;
        try {
            byte fileData[] = file.getBytes();
            fos = new FileOutputStream(path + "/" + fileName);
            fos.write(fileData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private String genSaveFileName(String extName) {

        Calendar calendar = Calendar.getInstance();
        String fileName = "";

        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += ("." + extName);

        return fileName;
    }

    //// TODO: 2016-06-29 Invalid Excel format handle
    public List<ApplicantVo> parseExcel(String filePath, int recruitId) {

        FileInputStream fis = null;
        String extName = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());

        List<ApplicantVo> list = new ArrayList<ApplicantVo>();

        try {
            fis = new FileInputStream(filePath);
            Workbook workbook = null;

            if ("xlsx".equals(extName)) {
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new HSSFWorkbook(fis);
            }

            int rowindex = 0;
            int columnindex = 0;

            //get sheet[0]
            Sheet sheet = workbook.getSheetAt(0);
            //# of rows
            int nRow = sheet.getPhysicalNumberOfRows();
            //from index 1
            for (rowindex = 1; rowindex < nRow; rowindex++) {
                ApplicantVo applicantVo = new ApplicantVo();
                //get row
                Row row = sheet.getRow(rowindex);
                if (row != null) {
                    int nCell = row.getPhysicalNumberOfCells();
                    for (columnindex = 0; columnindex <= nCell; columnindex++) {
                        //get cell
                        Cell cell = row.getCell(columnindex);
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
                        if (columnindex == 0) {
                            applicantVo.setName(value);
                        } else { //assume column index 1 is email.
                            applicantVo.setEmail(value);
                        }
                    }
                    applicantVo.setRecruitId(recruitId);
                    applicantVo.setTicket(applicantVo.getName() + applicantVo.getEmail()); //// TODO: 2016-06-29 any good method?
                    list.add(applicantVo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void sendTickets(int recruitId) {
        List<ApplicantVo> applicantList = applicantRepository.getListByRecruitId(recruitId);
        RecruitVo recruitVo = recruitRepository.get(recruitId);
        String emailContent = recruitVo.getEmailFormat();

        for (ApplicantVo vo : applicantList) {
            emailContent.replace("#이름", vo.getName());
            emailContent.replace("#링크", "127.0.0.1:8080/main");//"192.168.230.17:8080/?ticket=" + vo.getTicket() );

            sendMail(vo.getEmail(), emailContent);
        }
    }

    //todo convert to spring api, get message from user, content generateion function
    private void sendMail(String toAddr, String content) {// String companyName

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "localhost.localdomain");

        Session session = Session.getInstance(props, null);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@codit.com", "codit"));
//      InternetAddress[] address = {new InternetAddress(toAddr)};
//      msg.setRecipients(Message.RecipientType.TO, address);
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddr, "joon-ho"));
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
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}