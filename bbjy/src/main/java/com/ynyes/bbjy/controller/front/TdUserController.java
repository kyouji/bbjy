package com.ynyes.bbjy.controller.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.hibernate.engine.spi.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.ynyes.bbjy.entity.TdArea;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdAreaService;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.ClientConstant;

import net.sf.json.JSONObject;

/**
 * 用户中心
 * 
 * @author Sharon
 *
 */
@Controller
public class TdUserController {

	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@Autowired
	private TdAreaService tdAreaService;

	@RequestMapping(value = "/user/center")
	public String user(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
//		if (null == username) {
//			return "redirect:/login";
//		}

		tdCommonService.setHeader(map, req);
		if (null != username) {
			TdUser tdUser = tdUserService.findByUsername(username);
			
			if (null != tdUser) {
				map.addAttribute("user", tdUser);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String time = sdf.format(new Date());
				
				System.out.println(time);
				map.addAttribute("time", time);
			}
		}
		
		return "/client/user_center";
	}
	
	@RequestMapping(value = "/user/about")
	public String userAbout(HttpServletRequest req, ModelMap map) {
		
		tdCommonService.setHeader(map, req);
		String username = (String) req.getSession().getAttribute("username");
//		if (null == username) {
//			return "redirect:/login";
//		}

		if(null != username){
			TdUser tdUser = tdUserService.findByUsername(username);
			
			if (null == tdUser) {
				map.addAttribute("user", tdUser);
			}
		}

		return "/client/user_about";
	}


	@RequestMapping(value = "/user/comment/sec")
	public String commentSec(HttpServletRequest req, Long commentId, ModelMap map) {
		return "/client/comment_sec";
	}



	@RequestMapping("/user/check/oldpassword")
	@ResponseBody
	public Map<String, Object> checkOldPassword(HttpServletRequest req, String param) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", "n");

		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsername(username);
		if (!param.equals(user.getPassword())) {
			res.put("info", "当前密码输入错误！");
			return res;
		}
		res.put("status", "y");
		return res;
	}



	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	public String userInfo(Long hatu , HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			return "redirect:/login";
		}

		tdCommonService.setHeader(map, req);
		
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		map.addAttribute("user", user);
		map.addAttribute("hatu", hatu);

		return "/client/user_info";
	}

	@RequestMapping(value = "/user/info", method = RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> userInfo(HttpServletRequest req,
													String enterName,
													Long enterTypeId,
													String username,
													String realName, 
													String mobile, 
													Long[] photoIds,
													HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
	    res.put("code", 1);
		
		String usernameSession = (String) req.getSession().getAttribute("username");

		if (null == usernameSession) {
			res.put("msg", "请先登录！");
			res.put("login", 1);
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(usernameSession);
		
		if (null == realName ||realName.equals(""))
		{
			res.put("msg", "联系人姓名不能为空！");
			return res;
		}
		if (null == username ||username.equals(""))
		{
			res.put("msg", "用户名不能为空！");
			return res;
		}
		if (null == mobile || mobile.equals(""))
		{
			res.put("msg", "联系电话不能为空！");
			return res;
		}
		if(!usernamePattern(username)|| !usernamePattern(mobile))
		{
			res.put("msg", "用户名不能包含特殊字符");
			return res;
		}
		if(!lengthPattern(username)|| !lengthPattern(mobile))
		{
			res.put("msg", "用户名必须是6到18位字符");
			return res;
		}
		if(!isMobileNO(mobile))
		{
			res.put("msg", "电话号码格式不对！");
			return res;
		}

		TdUser user1 = tdUserService.findByUsername(username);
		if (null != user1 && user1.getId() != user.getId()) {
			res.put("msg", "该用户名已被注册！");
			return res;
		}
		TdUser user2 = tdUserService.findByMobile(mobile);
		if (null != user2 && user1.getId() != user.getId()) {
			res.put("msg", "该联系电话已被注册！");
			return res;
		}
		if(null != user.getRoleId() && user.getRoleId() == 0 && (null == enterName ||enterName.equals("")))
		{
			res.put("msg", "公司名称不能为空！");
			return res;
		}
		
		user.setUsername(username);
		user.setRealName(realName);
		user.setMobile(mobile);
		user = tdUserService.save(user);
		
		request.getSession().setAttribute("username", user.getUsername());
	    res.put("code", 0);
	    return res;
	}
	
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951|[0-9]{3})?([0-9]{8})|((13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}

	@RequestMapping(value = "/user/password", method = RequestMethod.GET)
	public String userPassword(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "/client/login";
		}
		TdUser user = tdUserService.findByUsername(username);

		map.addAttribute("user", user);
		return "/client/password_reset";

	}

	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword, String newPassword,
			ModelMap map) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		String username = (String) req.getSession().getAttribute("username");

		if (null == username) {
			res.put("msg", "请先登录！");
			return res;
		}

		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);

		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
		}

		map.addAttribute("user", tdUserService.save(user));

		res.put("code", 0);
		return res;
	}

	@RequestMapping("/user/password/save")
	public String savePassword(HttpServletRequest req, String newPassword) {
		String username = (String) req.getSession().getAttribute("username");
		TdUser user = tdUserService.findByUsername(username);
		user.setPassword(newPassword);
		tdUserService.save(user);
		
		return "/client/login";
	}
	
	//改变头像
	@RequestMapping(value = "/user/head")
	public String userHead(ModelMap map,  HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "redirect:/login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		map.addAttribute("user", user);
		
		return "client/user_head";
	}
	
	
	

    
    
    

    
    
    
	/**
	 * 图片地址字符串整理，多张图片用,隔开
	 * 
	 * @param params
	 * @return
	 */
	private String parsePicUris(String[] uris) {
		if (null == uris || 0 == uris.length) {
			return null;
		}

		String res = "";

		for (String item : uris) {
			String uri = item.substring(item.indexOf("|") + 1, item.indexOf("|", 2));

			if (null != uri) {
				res += uri;
				res += ",";
			}
		}

		return res;
	}
	
	public boolean usernamePattern(String username){
		Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher m = p.matcher(username);
		return m.matches();
	}
	public boolean lengthPattern(String username){
		Pattern p = Pattern.compile("^.{6,18}$");
		Matcher m = p.matcher(username);
		return m.matches();
	}
}
