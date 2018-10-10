package com.dogfriend.swp.controller;



import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dogfriend.swp.util.FileUtils;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadFormGET() {
		logger.info("upload GET......");
	}
	
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFormAJAX(MultipartFile file, @RequestParam String type) throws Exception {
		logger.info("upload AJAX.... originalName={}, size={}, contentType={}",
					file.getOriginalFilename(),
					file.getSize(),
					file.getContentType());
		logger.info(".......type={}", type);
		
		try {
			String savedFileName = FileUtils.uploadFile(file, uploadPath);
			return new ResponseEntity<>(savedFileName, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public void uploadFormPOST(MultipartFile file, Model model, @RequestParam String type) throws Exception {
		logger.info("upload POST.... originalName={}, size={}, contentType={}",
					file.getOriginalFilename(),
					file.getSize(),
					file.getContentType());
		String savedFileName = FileUtils.uploadFile(file, uploadPath);
		model.addAttribute("savedFileName", savedFileName);
		model.addAttribute("type", type);
	}
	
}
