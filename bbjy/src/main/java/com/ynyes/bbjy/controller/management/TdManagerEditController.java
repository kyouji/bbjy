package com.ynyes.bbjy.controller.management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.bbjy.entity.TdArticle;
import com.ynyes.bbjy.entity.TdArticleCategory;
import com.ynyes.bbjy.service.TdArticleCategoryService;
import com.ynyes.bbjy.service.TdArticleService;
import com.ynyes.bbjy.service.TdManagerLogService;

/**
 * 后台首页控制器
 * 
 * @author Sharon
 */

@Controller
@RequestMapping(value="/Verwalter")
public class TdManagerEditController {
    
    @Autowired
    TdArticleCategoryService tdArticleCategoryService;
    
    @Autowired
    TdArticleService tdArticleService;
    
    @Autowired
    TdManagerLogService tdManagerLogService;
    
    @RequestMapping(value="/category/edit")
    public String categoryEditDialog(Long cid, Long mid, Long id, Long sub, 
                                    String __EVENTTARGET,
                                    String __EVENTARGUMENT,
                                    String __VIEWSTATE,
                                    ModelMap map,
                                    HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }

        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
//        if (null != cid && cid.equals(2L)) // 商品分类
//        {
//            map.addAttribute("category_list", tdProductCategoryService.findAll());
//            
//            // 参数类型表
//            map.addAttribute("param_category_list", tdParameterCategoryService.findAll());
//            
//            if (null != sub) // 添加子类
//            {
//                if (null != id)
//                {
//                    map.addAttribute("fatherCat", tdProductCategoryService.findOne(id));
//                }
//            }
//            else
//            {
//                if (null != id)
//                {
//                    map.addAttribute("cat", tdProductCategoryService.findOne(id));
//                }
//            }
//            
//            return "/site_mag/product_category_edit";
//        }
//        else
        {
            if (null != mid)
            {
                map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
            }
            
            if (null != id)
            {
                if (null != sub) // 添加子类
                {
                    map.addAttribute("fatherCat", tdArticleCategoryService.findOne(id));
                }
                else
                {
                    map.addAttribute("cat", tdArticleCategoryService.findOne(id));
                }
            }
            
            return "/site_mag/article_category_edit";
        }
    }
    
    @RequestMapping(value="/category/save", method = RequestMethod.POST)
    public String save(TdArticleCategory cat, 
                        String __EVENTTARGET,
                        String __EVENTARGUMENT,
                        String __VIEWSTATE,
                        ModelMap map,
                        HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username)
        {
            return "redirect:/Verwalter/login";
        }
        
        if (null == cat.getId())
        {
            tdManagerLogService.addLog("add", "用户修改文章分类", req);
        }
        else
        {
            tdManagerLogService.addLog("edit", "用户修改文章分类", req);
        }
        
        tdArticleCategoryService.save(cat);
        
        return "redirect:/Verwalter/category/list?cid=" + cat.getChannelId() 
                + "&mid=" + cat.getMenuId()
                + "&__EVENTTARGET=" + __EVENTTARGET
                + "&__EVENTARGUMENT=" + __EVENTARGUMENT
                + "&__VIEWSTATE=" + __VIEWSTATE;
    }
    
    @RequestMapping(value="/article/edit")
    public String articleEditDialog(Long cid, Long mid, Long pid, Long id, 
            String __EVENTTARGET,
            String __EVENTARGUMENT,
            String __VIEWSTATE,
            ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        
        map.addAttribute("cid", cid);
        map.addAttribute("mid", mid);
        map.addAttribute("__EVENTTARGET", __EVENTTARGET);
        map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
        map.addAttribute("__VIEWSTATE", __VIEWSTATE);
        
        if (null != id)
        {
            map.addAttribute("article", tdArticleService.findOne(id));
        }
        
        if (null != mid)
        {
            map.addAttribute("category_list", tdArticleCategoryService.findByMenuId(mid));
            
            if(mid == 8 || mid==11)
            {
            	return "/site_mag/article_content_info_edit";
            }
        }
        
        return "/site_mag/article_content_edit";
    }
    
    @RequestMapping(value="/article/save", method = RequestMethod.POST)
    public String save(TdArticle article,
    		String theDate,
    	    String[] hid_photo_name_show360,
            String __EVENTTARGET,
            String __EVENTARGUMENT,
            String __VIEWSTATE,
            ModelMap map,
            HttpServletRequest req){
        String username = (String) req.getSession().getAttribute("manager");
        if (null == username) {
            return "redirect:/Verwalter/login";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        if(null != theDate && !theDate.equals("")){
        	try {
				date = sdf.parse(theDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        article.setDate(date);		
        
        String uris = parsePicUris(hid_photo_name_show360);

        article.setShowPictures(uris);
        
        String logType = null;
        if (null == article.getId())
        {
            logType = "add";
        }
        else
        {
            logType = "edit";
        }
        tdArticleService.save(article);
        
        tdManagerLogService.addLog(logType, "用户修改文章", req);
        
        return "redirect:/Verwalter/content/list?cid=" + article.getChannelId() 
                + "&mid=" + article.getMenuId()
                + "&__EVENTTARGET=" + __EVENTTARGET
                + "&__EVENTARGUMENT=" + __EVENTARGUMENT
                + "&__VIEWSTATE=" + __VIEWSTATE;
    }
    
    
    @RequestMapping(value = "/list/dialog/article")
	public String ListDialogArticle( 
			Integer size, Integer total, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		
		// 参数注回
		map.addAttribute("total", total);
		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		return "/site_mag/dialog_article_list";
	}
    
    @RequestMapping(value = "/list/dialog/pro")
	public String ListDialogPro( 
			Integer size, Integer pro, String __EVENTTARGET, String __EVENTARGUMENT, String __VIEWSTATE, ModelMap map,
			HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("manager");
		if (null == username) {
			return "redirect:/Verwalter/login";
		}
		
		// 参数注回
		map.addAttribute("pro", pro);
		map.addAttribute("__EVENTTARGET", __EVENTTARGET);
		map.addAttribute("__EVENTARGUMENT", __EVENTARGUMENT);
		map.addAttribute("__VIEWSTATE", __VIEWSTATE);

		return "/site_mag/dialog_pro_list";
	}
    
    /**
     * 图片地址字符串整理，多张图片用,隔开,从goods搬过来
     * @author Zhangji
     * @param params
     * @return
     */
    private String parsePicUris(String[] uris) {
        if (null == uris || 0 == uris.length) {
            return null;
        }

        String res = "";

        for (String item : uris) {
            String uri = item.substring(item.indexOf("|") + 1,
                    item.indexOf("|", 2));

            if (null != uri) {
                res += uri;
                res += ",";
            }
        }

        return res;
    }
    
}
