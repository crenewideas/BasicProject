package cn.pxl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/fileUpload")
public class FileController {
// fileUpload/upload/page
    @RequestMapping("/upload/page")
    public String doGetPage(){
        return "jsp/upload";
    }

    //使用 HttpServletRequest 作为请求参数。
    @RequestMapping("/upload/request")
    @ResponseBody
    public Map<String,Object> doUpload(HttpServletRequest request){
        boolean flag = false;
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        MultipartHttpServletRequest multipartHttpServletRequest;
        if(request instanceof MultipartHttpServletRequest){
            multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        }else {
            stringObjectHashMap.put("false","失败");
            return stringObjectHashMap;
        }
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String originalFilename = file.getOriginalFilename();
        File newFile = new File(originalFilename);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            stringObjectHashMap.put("false","失败");
        }
        stringObjectHashMap.put("true","成功");
        return stringObjectHashMap;
    }

    //使用 MultipartFile 作为请求参数。
    @RequestMapping("/upload/requestMultiparFile")
    @ResponseBody
    public Map<String,Object> doUploadBy(MultipartFile multipartFile){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        String originalFilename = multipartFile.getOriginalFilename();
        File newFile = new File(originalFilename);
        try {
            multipartFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            stringObjectHashMap.put("false","失败");
        }
        stringObjectHashMap.put("true","成功");
        return stringObjectHashMap;
    }

    //使用 Part 作为请求参数。
    @RequestMapping("/upload/requestPart")
    @ResponseBody
    public Map<String,Object> doUploadBy(Part multipartFile){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        String originalFilename = multipartFile.getSubmittedFileName();
        try {
            multipartFile.write(originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
            stringObjectHashMap.put("false","失败");
        }
        stringObjectHashMap.put("true","成功");
        return stringObjectHashMap;
    }


}
