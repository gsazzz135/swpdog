package com.dogfriend.util.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dogfriend.swp.util.FileUtils;



public class FileUtilsTest {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);
	private static final String uploadRootPath = "C:\\Users\\gsa23\\Documents\\workspace-sts-3.9.5.RELEASE\\upload";
	
	@Ignore @Test
	public void test() {
		assertTrue(existsDir(uploadRootPath));
		
		String path = FileUtils.getCurrentUploadPath(uploadRootPath);
		logger.debug("path={}", path);
		assertTrue(existsDir(path));
	}
	
	@Ignore @Test
	public void testThumbnail() throws IOException{
		String dirname = "C:\\Users\\gsa23\\Documents\\workspace-sts-3.9.5.RELEASE\\upload\\2018\\09\\21";
		String filename = "1app_main.png";
		String thumbname = "s_" + filename;
		logger.info("dirname={}, filename={}", dirname, filename);
		
		File old = new File(dirname, thumbname);
		if(old.exists())
		   old.delete();
		
		String thumbnailName = FileUtils.makeThumbnail(uploadRootPath, dirname, filename);
		logger.info("thumbnailName={}", thumbnailName);
		assertEquals(thumbnailName, "2018/09/21/" + thumbname);
		assertTrue(existsDir(dirname + File.separator + thumbname));
	}
	
	private boolean existsDir(String path) {
		return new File(path).exists();
	}
}
