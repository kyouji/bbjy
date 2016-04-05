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
        
//         统计在线人数
//        if (null != setting && null == req.getSession().getAttribute("countedTotalOnlines"))
//        {
//            req.getSession().setAttribute("countedTotalOnlines", "yes");
//            if (null == setting.getTotalOnlines())
//            {
//                setting.setTotalOnlines(1L);
//            }
//            else
//            {
//                setting.setTotalOnlines(setting.getTotalOnlines() + 1L);
//            }
//            setting = tdSettingService.save(setting);
//        }

        map.addAttribute("site", setting);
//        map.addAttribute("keywords_list",
//                tdKeywordsService.findByIsEnableTrueOrderBySortIdAsc());


        // 帮助中心
//        Long helpId = 12L;
//
//        map.addAttribute("help_id", helpId);
//
//        List<TdArticleCategory> level0HelpList = tdArticleCategoryService
//                .findByMenuIdAndParentId(helpId, 0L);
//
//        map.addAttribute("help_level0_cat_list", level0HelpList);

//        if (null != level0HelpList) {
//
//            for (int i = 0; i < level0HelpList.size() && i < 4; i++) {
//                TdArticleCategory articleCat = level0HelpList.get(i);
//                map.addAttribute("help_" + i + "_cat_list",
//                        tdArticleCategoryService.findByMenuIdAndParentId(
//                                helpId, articleCat.getId()));
//            }
//        }

        // 友情链接
//        map.addAttribute("site_link_list",
//                tdSiteLinkService.findByIsEnableTrueOrderBySortIdAsc());
    
    }

}