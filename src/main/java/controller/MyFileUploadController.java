package controller;


import form.MyUploadForm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyFileUploadController {
    // phuong thuc nay duoc goi moi lan co submit
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        Object target = dataBinder.getTarget();
        if (target == null){
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == MyUploadForm.class){
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
    //GET: hien thi trang form upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
    public String uploadOneFileHandler(Model model){
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
        return "uploadOneFile";
    }

    //POST: xu li upload
    @RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST)
    public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("myUploadForm")MyUploadForm myUploadForm){
        return this.doUpload(request, model, myUploadForm);
    }

    //GET:Hien thi trang form upload
    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.GET)
    public String uploadMultiFileHandler(Model model){
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUploadForm", myUploadForm);
        return "uploadMultiFile";
    }
    //POST: xu li upload
    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    public String uploadMultiFileHandlerPOST(HttpServletRequest request, Model model, @ModelAttribute("myUploadForm")MyUploadForm myUploadForm){
        return this.doUpload(request, model, myUploadForm);
    }

    private String doUpload(HttpServletRequest request, Model model, MyUploadForm myUploadForm){
        String description = myUploadForm.getDescription();
        System.out.println("Description:" + description);
        // Thu muc goc upload file
        String uploadRootPath = request.getServletContext().getRealPath("upload");
        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        // tao thu muc goc upload neu no khong ton tai
        if (!uploadRootDir.exists()){
            uploadRootDir.mkdirs();
        }
        MultipartFile[] fileDatas = myUploadForm.getFileDatas();
        Map<File, String> uploadedFiles = new HashMap<>();
        for (MultipartFile fileData : fileDatas){
            //Ten file goc tai client
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);
            if (name != null && name.length() > 0){
                try {
                    //tao file tai sever
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    //luong ghi du lieu vao file tren server
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();
                    uploadedFiles.put(serverFile, name);
                    System.out.println("Write file: " + serverFile);
                }catch (Exception e){
                    System.out.println("Error write file: " + name);
                }
            }
        }
        model.addAttribute("description", description);
        model.addAttribute("uploadedFile", uploadedFiles);
        return "uploadResult";
    }
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSizePerFile(10000000);
        return multipartResolver;
    }
}
