package com.ynyes.bbjy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ynyes.bbjy.entity.TdSetting;
import com.ynyes.bbjy.entity.TdUser;

@Service
public class TdCommonService {

    @Autowired
    private TdSettingService tdSettingService;
    
    @Autowired
    private TdUserService tdUserService;

//    @Autowired
//    private TdKeywordsService tdKeywordsService;
//
//    @Autowired
//    private TdArticleCategoryService tdArticleCategoryService;


    public void setHeader(ModelMap map, HttpServletRequest req) {
        
        // 网站基本信息
        TdSetting setting = tdSettingService.findTopBy();
        
        String username = (String) req.getSession().getAttribute("username");
        if (null !=username){
            TdUser user = tdUserService.findByUsername(username);
            if (null != user)
            {
            	map.addAttribute("user", user);
            }
        }

        map.addAttribute("site", setting);
    
    }

}
