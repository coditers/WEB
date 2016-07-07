package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.ApplicantRepository;
import com.estsoft.codit.db.repository.CartRepository;
import com.estsoft.codit.db.repository.ProblemInfoRepository;
import com.estsoft.codit.db.repository.RecruitRepository;
import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.db.vo.CartVo;
import com.estsoft.codit.db.vo.ProblemInfoVo;
import com.estsoft.codit.db.vo.RecruitVo;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.jms.Session;


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



  private static final String FILE_SAVE_PATH = "/temp/";


  public RecruitVo getRecruitVo(int id) {
    return recruitRepository.get(id);
  }

  public int insert(RecruitVo recruitVo) {
    return recruitRepository.insert(recruitVo);
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

  public void insertApplicantList(List<ApplicantVo> list){
    applicantRepository.insertList(list);
  }

  public List<ProblemInfoVo> getProblemInfoVoList( ){
    return problemInfoRepository.getList();
  }

  public void insertCart( CartVo vo){
    cartRepository.insert( vo );
  }

  public void sendTickets( int recruitId ){

  }
}
