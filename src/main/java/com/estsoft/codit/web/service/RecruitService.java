package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.*;
import com.estsoft.codit.db.vo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

  public List<RecruitVo> getRecruitListByClientId(int clientId){ return recruitRepository.getListByClientId(clientId);}

  public List<ProblemInfoVo> getProblemInfoVoList( ){
    return problemInfoRepository.getList();
  }


  public int insert(RecruitVo recruitVo) {
    return recruitRepository.insert(recruitVo);
  }

  public void insertApplicantList(List<ApplicantVo> list){
    applicantRepository.insertList(list);
  }

  public void insertCart( CartVo vo){
    cartRepository.insert( vo );
  }

  public List<ProblemInfoVo> getProblemInfoList( int recruitId ){
    return problemInfoRepository.getProblemInfoList(recruitId);
  }

  public List<ApplicantVo> getApplicantList ( int  recruitId){
    return applicantRepository.getListByRecruitId(recruitId);
  }

  public List<ResultVo> getResultList( int applicantId, int problemInfoId ){
    return resultRepository.getResultList( applicantId, problemInfoId );
  }

  public void setRecruitDate(int recruitId, String fromDate, String toDate){
    recruitRepository.updateRecruitDate(recruitId, fromDate, toDate);
  }

  public boolean isApplicantRegistered( int recruitId){
    return applicantRepository.getListByRecruitId( recruitId ).isEmpty() == false;
  }

  public boolean isRecruitDateSet( int recruitId){
    RecruitVo vo = recruitRepository.get(recruitId);

    if("".equals(vo.getFromDate()) || vo.getFromDate() == null)
      return false;

    return true;
  }

  public boolean isProblemSelected( int recruitId){
    return cartRepository.getListByRecruitId(recruitId).isEmpty() == false;
  }

  public int calcApplicantCorrectionRate(int applicantId, List<ProblemInfoVo> probInfoList){

      int correctionRate = 0;
      int nTotalTestCase = 0;
      for (ProblemInfoVo probVo : probInfoList){
        List<ResultVo> resultList = getResultList(applicantId, probVo.getId());
      }

    return 12;
  }
  public int calcApplicantProblemCorrectionRate(int applicantId, int problemInfoId){

    int correctionRate = 0;
    int nTotalTestCase = 0;

    List<ResultVo> resultList = getResultList(applicantId, problemInfoId);


    return 12;
  }
  public boolean isContained(int recruitId, int clientId){

    RecruitVo vo = recruitRepository.getByIdClientId(recruitId, clientId);
    if(vo != null)
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
    }else
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
  public List<ApplicantVo> parseExcel( String filePath, int recruitId) {

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
            if(columnindex == 0){
              applicantVo.setName(value);
            }else{ //assume column index 1 is email.
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

  public void sendTickets( int recruitId ){
    String link = "127.0.0.1:8080/main";//"192.168.230.17:8080/?ticket=";
    List<ApplicantVo> appList = applicantRepository.getListByRecruitId( recruitId );
//    for ( ApplicantVo vo : appList){
//      //todo mail content!
//      sendMail( vo.getEmail(), link + vo.getTicket());
//    }
      sendMail("joonhoyeom@gmail.com", link);
  }

  //todo convert to spring api, get message from user, content generateion function
  private void sendMail( String toAddr, String content){// String companyName

    Properties props = System.getProperties();
    props.setProperty("mail.smtp.host", "localhost.localdomain");

    Session session = Session.getInstance(props, null);

    try {
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("noreply@codit.com", "estsoft"));
//      InternetAddress[] address = {new InternetAddress(toAddr)};
//      msg.setRecipients(Message.RecipientType.TO, address);
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress( toAddr, "joon-ho"));
      msg.setSubject("제목이 이러저러합니다");
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
      msg.setContent("http://115.68.116.235:8080/", "text/html; charset=utf-8");

      Transport.send(msg);
    } catch (Exception ex) {
      System.out.println( ex );
    }
  }
}