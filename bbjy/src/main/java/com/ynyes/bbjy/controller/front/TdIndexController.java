package com.ynyes.bbjy.controller.front;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ynyes.bbjy.entity.TdArticle;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdArticleService;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdUserService;


/**
 *@author Zhangji
 */
@Controller
@RequestMapping
public class TdIndexController {

    
    @Autowired
    TdCommonService tdCommonService;
    
    @Autowired
    TdUserService tdUserService;
    
    @Autowired
    TdArticleService tdArticleService;

    
    
    
    @RequestMapping("/")
    public String index(String code,HttpServletRequest req, Device device, ModelMap map) {        
    	tdCommonService.setHeader(map, req); 
        String username = (String) req.getSession().getAttribute("username");
        if (null !=username){
//            TdUser user = tdUserService.findByUsername(username);
//            if (null != user)
//            {
//            	map.addAttribute("user", user);
//            }
        }

        //讯息
        List<TdArticle> infoList = tdArticleService.findByMenuId(8L);
        map.addAttribute("info_list", infoList);
        //行销资源
        infoList = tdArticleService.findByMenuId(11L);
        map.addAttribute("source_list", infoList);
        infoList = null;
        
        return "/client/index";
       
        
    }
    
    @RequestMapping("/index")
    public String Index(String code, HttpServletRequest req, Device device, ModelMap map) {        
    	tdCommonService.setHeader(map, req); 
        String username = (String) req.getSession().getAttribute("username");
        if (null !=username){
            TdUser user = tdUserService.findByUsername(username);
            if (null != user)
            {
            	map.addAttribute("user", user);
            }
        }

        return "/client/index";
    }
    
    
	
}
