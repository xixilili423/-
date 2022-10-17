package com.example.demo.action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created   on 2019-11-22.
 */
public class SyncAntiPayeeC {



    protected void download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //  multipart/form-data
        boolean isMultipart = ServletFileUpload. isMultipartContent(request);
        if (!isMultipart) {
            System.out.println("ok");
        }else{
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (FileItem fileItem : fileItems) {
                    if (fileItem.isFormField()) {
                        System.out.println(fileItem.getFieldName()+"\t"+fileItem.getString("UTF-8"));
                    } else {
                        String fileName = fileItem.getName();
                        System.out.println(fileName);
                        String ext = fileName.substring(fileName.lastIndexOf("."));
                        String name = java.util.UUID.randomUUID ()+ext;
                        File file = new File("workspace" + File.separator +name);
                        fileItem.write(file);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


