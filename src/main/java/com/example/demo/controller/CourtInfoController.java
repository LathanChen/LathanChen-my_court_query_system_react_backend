package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CourtEvaluate;
import com.example.demo.entity.CourtInfo;
import com.example.demo.entity.CourtOpenInfo;
import com.example.demo.entity.PaginationResult;
import com.example.demo.entity.ResponseResult;
import com.example.demo.service.CourtInfoService;

@RestController
@RequestMapping("/courtinfo")
public class CourtInfoController {
//	开发环境下图片上传后的存储路径
	private static final String UPLOAD_DEVELOPMENT_URL = "D:/react/my_court_query_system/my_court_query_system/public/images/courtInfoImages";
//	开发环境下图片上传后，拼接文件名返回给前端的路径前缀
	private static final String UPLOAD_DEVELOPMENT_URL_SHORT = "/images/courtInfoImages/";
//	允许上传文件的类型
	private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");

	@Autowired
    private CourtInfoService courtInfoService;

	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<CourtInfo> getCourts(){
		return (ArrayList<CourtInfo>) courtInfoService.getCourts();
	}

	@RequestMapping(value="/getcourtnames",method=RequestMethod.GET)
	@ResponseBody
	public PaginationResult<CourtInfo> getCourtNames(int PageNum,int PageSize){
		return (PaginationResult<CourtInfo>)courtInfoService.getCourtNames(PageNum,PageSize);
	}

	@RequestMapping(value="/getCourtImgs",method=RequestMethod.GET)
	@ResponseBody
	public List<String> getCourtImgUrls(
			@RequestParam("courtId") int courtId){
		return courtInfoService.getCourtImgUrls(courtId);
	}

	@RequestMapping(value="/getAllCourtInfoById",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAllCourtInfoById(
			@RequestParam("courtId") int courtId){
		return courtInfoService.getAllCourtInfoById(courtId);
	}

	@RequestMapping(value="/setScroeAndComment",method=RequestMethod.POST)
	@ResponseBody
	public int setCourtEvaluate(@RequestBody CourtEvaluate courtEvaluate) {
		return courtInfoService.setCourtEvaluate(courtEvaluate);
	}

	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> uploadImg(@RequestParam("file") MultipartFile file) {
//		文件为空check
		if (file.isEmpty()) {
//			状态码202：已经接受请求，但未处理完成
            return new ResponseResult<String>(202, "文件为空！",null);
        }

//		 文件格式check
		 String contentType = file.getContentType();
		 if(!ALLOWED_CONTENT_TYPES.contains(contentType)) {
			 return new ResponseResult<String>(202, "请上传jpg或png格式的文件！",null);
		 }

//		 尝试存储文件
        try {
            // 创建存储目录（如果不存在）
            File directory = new File(UPLOAD_DEVELOPMENT_URL);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 生成新的文件名
//            old => 时间戳+原文件名拼接,如果原文件名中有中文字符串,会导致前端url的ASCII编码问题,导致图片无法预览
            String originalFilename = file.getOriginalFilename();
//            判断文件名中是否有非ASCII编码的字符
            for (char c :originalFilename.toCharArray()) {
            	if ((int)c > 127) {
            		return new ResponseResult<String>(202,"文件名只能包含字母和数字!",null);
            	}
            }
            String newFilename = System.currentTimeMillis() + "_" + originalFilename;

//          生成新的文件名 => 系统时间戳
//          获取文件后缀
//            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String newFilename = System.currentTimeMillis() + fileSuffix;
            File destFile = new File(directory, newFilename);

            // 存储文件
            file.transferTo(destFile);

            String filePath = destFile.getAbsolutePath();
            return new ResponseResult<String>(200,"文件上传成功！",UPLOAD_DEVELOPMENT_URL_SHORT+newFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseResult<String>(500,"文件上传失败！",null);
        }
    }

	@RequestMapping(value="/checkSameCourtName",method=RequestMethod.GET)
	@ResponseBody
	public boolean checkSameCourtName(
			@RequestParam("courtName") String courtName){
		return courtInfoService.checkSameCourtName(courtName);
	}

	@RequestMapping(value="/setCourtInfoAndImgs",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> setCourtInfoAndImgs(
			@RequestBody Map<String,Object> courtInfoAndImgs){
		System.out.println(courtInfoAndImgs);
		courtInfoService.setCourtInfoAndImgs(courtInfoAndImgs);
		return new ResponseResult<String>(200,"文件上传成功！","aaa");

//		return courtInfoService.setCourtInfoAndImgs(courtInfoAndImgs);
	}
	}
