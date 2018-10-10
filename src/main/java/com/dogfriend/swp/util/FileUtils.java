package com.dogfriend.swp.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
	
	private static Map<String, MediaType> mediaMap;
	static {
		mediaMap = new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String ext) {
		return mediaMap.get(ext.toUpperCase());
	}
	
	public static String uploadFile(MultipartFile file, String uploadPath) throws IOException {
		String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
		String dirname = getCurrentUploadPath(uploadPath);
		File target =new File(dirname, filename);
		FileCopyUtils.copy(file.getBytes(), target);				//file.getBytes() ?��?�� ?��?��?�� �??��?��?��
		
		String ext = getFileExtension(filename);
		
		String uploadedFilename = null;
		if (getMediaType(ext) != null) {
			uploadedFilename = makeThumbnail(uploadPath, dirname, filename); 
		} else {
			uploadedFilename = makeIcon(uploadPath, dirname, filename);
		}
		
		return uploadedFilename;
	}
	
	private static String makeIcon(String uploadPath, String dirname, String filename) {
		String iconName = dirname + File.separator + filename;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	public static String makeThumbnail(String uploadRootPath, String dirname, String filename) throws IOException {
		BufferedImage srcImg = ImageIO.read(new File(dirname, filename));
		BufferedImage destImg = Scalr.resize(srcImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = dirname + File.separator + "s_" + filename;
		String ext = getFileExtension(filename);
		File newFile = new File(thumbnailName);
		ImageIO.write(destImg, ext.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadRootPath.length()).replace(File.separatorChar, '/');
	}

	private static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	public static String getCurrentUploadPath(String uploadRootPath) {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		
		return makeDir(uploadRootPath, "" + y, len2(m), len2(d));	
	}

	private static String makeDir(String uploadRootPath, String... paths) {
		for (String path: paths) {
			uploadRootPath += File.separator + path;
			File tmpFile = new File(uploadRootPath);
			if(tmpFile.exists()) {
				continue;
			} else {
				tmpFile.mkdir();
			}
		}
		return uploadRootPath;
	}

	private static String len2(int n) {
		return new DecimalFormat("00").format(n).toString();
	}
	public static void main(String[] args) {
		getCurrentUploadPath("/aaa");
	}
}
