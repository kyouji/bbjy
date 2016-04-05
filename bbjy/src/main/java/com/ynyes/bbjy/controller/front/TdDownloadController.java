package com.ynyes.bbjy.controller.front;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.bbjy.entity.TdArticle;
import com.ynyes.bbjy.entity.TdArticleData;
import com.ynyes.bbjy.entity.TdNavigationMenu;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdArticleDataService;
import com.ynyes.bbjy.service.TdArticleService;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdNavigationMenuService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.SiteMagConstant;

import net.sf.json.JSONObject;

@Controller
public class TdDownloadController {
	
	@Autowired
    private TdCommonService tdCommonService;
	
	@Autowired
	private TdArticleDataService tdArticleDataService;
	
	@Autowired
	private TdArticleService tdArticleService;
	
	String filepath = SiteMagConstant.imagePath;
	 
	 //下载文件 tdArticleDataData
	 @RequestMapping(value="/download/data", method = RequestMethod.GET)
	    @ResponseBody
	    public void downloadArticle(Long id,
	                HttpServletResponse resp,
	                HttpServletRequest req) throws IOException {
	        if (null == id)
	        {
	            return;
	        }
	        
	        OutputStream os = resp.getOutputStream();  
	        
	        TdArticleData tdArticleData = tdArticleDataService.findOne(id);
	        if(null == tdArticleData){
	        	return;
	        }
	        //文件名 
	        String filename = tdArticleData.getTitle();
	        if(null != tdArticleData.getFilename() && !tdArticleData.getFilename().equals("")){
	        	filename = tdArticleData.getFilename();
	        }
	        
	        //文件路径
	        String name = tdArticleData.getUrl();
	        String ext = name.substring(name.lastIndexOf("."));
//	        name = name.substring(name.indexOf("/",2));
	        File file = new File(filepath +"/" + name);
	        
	        if (file.exists())
	        {
	            try {
	                resp.reset();
	                resp.setHeader("Content-Disposition", "attachment; filename="
	                		+URLEncoder.encode(filename+ext, "UTF-8"));
	                resp.setContentType("application/octet-stream; charset=utf-8");
	                os.write(FileUtils.readFileToByteArray(file));
	                os.flush();
	            } finally {
	                if (os != null) {
	                    os.close();
	                }
	            }
	        }
	        else 
	        {
	        	return;
	        }
	    }
	 
	//下载文件 tdArticle
	@RequestMapping(value="/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(Long id,
                HttpServletResponse resp,
                HttpServletRequest req) throws IOException {
        if (null == id)
        {
            return;
        }
        
        OutputStream os = resp.getOutputStream();  
        
        TdArticle tdArticle = tdArticleService.findOne(id);
        if(null == tdArticle){
        	return;
        }
        //文件名 
        String filename = tdArticle.getTitle();
        if(null != tdArticle.getFilename() && !tdArticle.getFilename().equals("")){
        	filename = tdArticle.getFilename();
        }
        
        //文件路径
        String name = tdArticle.getImgUrl();
        String ext = name.substring(name.lastIndexOf("."));
        name = name.substring(name.indexOf("/",2));
        File file = new File(filepath +"/" + name);
        
        if (file.exists())
        {
            try {
                resp.reset();
                resp.setHeader("Content-Disposition", "attachment; filename="
                		+URLEncoder.encode(filename+ext, "UTF-8"));
                resp.setContentType("application/octet-stream; charset=utf-8");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
        else 
        {
        	return;
        }
    }
}
