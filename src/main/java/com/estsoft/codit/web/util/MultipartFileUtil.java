package com.estsoft.codit.web.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by estsoft on 2016-07-26.
 */
public class MultipartFileUtil {

    //!** directory path in server environment
    private static final String FILE_SAVE_PATH = "/home/joonho/www/codit/multipartData/";


    /**
     * save multipart file,
     * @return succss : file path, fail : null
     */
    public static String saveMultiPartFile(MultipartFile file) {

        if (file.isEmpty() == false) {

            String fileOriginalName = file.getOriginalFilename();
            String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length());
            String saveFileName = genSaveFileName(extName);

            writeFile(file, FILE_SAVE_PATH, saveFileName);
            return FILE_SAVE_PATH + saveFileName;
        } else
            return null;
    }
    private static void writeFile(MultipartFile file, String path, String fileName) {
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
    private static String genSaveFileName(String extName) {

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
}
