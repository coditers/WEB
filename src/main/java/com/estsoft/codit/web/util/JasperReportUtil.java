package com.estsoft.codit.web.util;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sun on 2016-08-16.
 */

@Component
public class JasperReportUtil extends Exception {

  private static BasicDataSource dataSource;

  @Autowired
  private JasperReportUtil(BasicDataSource dataSource){
    this.dataSource = dataSource;
  }

  public static Map<String, Object> render(int recruit_id, int applicant_id) {
    Map<String,Object> parameterMap = new HashMap<String,Object>();
    try {
      String path = JasperReportUtil.class.getClassLoader().getResource("/").getPath();
      path = path.substring(0, path.length()-1);
      path = path.substring(0, path.lastIndexOf("/")+1);
      path = path+"reports/";

      parameterMap.put("ID_RECRUIT", recruit_id);
      parameterMap.put("ID_APPLICANT", applicant_id);
      parameterMap.put("CONTEXT", path );
      parameterMap.put("format", "pdf");
      parameterMap.put("REPORT_CONNECTION", dataSource.getConnection());

    }catch (Exception e){
      e.printStackTrace();
    }
    return parameterMap;
  }
}
