package com.ynyes.bbjy.controller.front;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdSettingService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.VerifServlet;

import net.sf.json.JSONObject;

/**
 * 注册处理
 * 
 */
@Controller
public class TdRegController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdSettingService tdSettingService;

	@Autowired
	private TdCommonService tdCommonService;
	
	@RequestMapping("/reg")
	public String reg(/* Integer shareId, */String name, String code,  Long type, HttpServletRequest request,
			ModelMap map) {
		String username = (String) request.getSession().getAttribute("username");

//		if (null != shareId) {
//			map.addAttribute("share_id", shareId);
//		}
		// 基本信息
		tdCommonService.setHeader(map, request);

		if (null == username) {
			
			map.addAttribute("username", name);
			
			if (null != type &&type == 1)
			{
				map.addAttribute("changeRole", 1);
				return "/client/reg_acc";
			}
			else{
				map.addAttribute("changeRole", 0);
				return "/client/reg_enter";
			}
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/logutil")
	public String LogUtils() {
		return "/logutil";
	}

	/**
	 * 
	 * 注册用户保存到数据库<BR>
	 * 方法名：saveUser<BR>
	 * 时间：2015年2月2日-下午1:44:45 <BR>
	 * 
	 * @param user
	 * @param name
	 * @param mobile
	 * @param password
	 * @param newpassword
	 * @return String<BR>
	 * @param [参数1]
	 *            [参数1说明]
	 * @param [参数2]
	 *            [参数2说明]
	 * @exception <BR>
	 * @since 1.0.0
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> reg(String username, 
									String mobile, 
									String password, 
									String password2, 
									String realName,
									String enterName,
									String enterType, 
									Long enterTypeId,
									Long roleId,
									Long changeRole,
									ModelMap map,
									HttpServletRequest request) {
	    Map<String, Object> res = new HashMap<String, Object>();
	    res.put("code", 1);
		
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
		if(!usernamePattern(username)|| !usernamePattern(password))
		{
			res.put("msg", "用户名和密码不能包含特殊字符");
			return res;
		}
		if(!lengthPattern(username)|| !lengthPattern(password))
		{
			res.put("msg", "用户名和密码必须是6到18位字符");
			return res;
		}
		if (null == mobile || mobile.equals(""))
		{
			res.put("msg", "联系电话不能为空！");
			return res;
		}
		if(!isMobileNO(mobile))
		{
			res.put("msg", "电话号码格式不对！");
			return res;
		}
		if(null == password || password.equals(""))
		{
			res.put("msg", "密码不能为空！");
			return res;
		}
		if (null == password2 || password2.equals("") || null != password2 && !password2.equals(password))
		{
			res.put("msg", "两次输入密码不一致！");
			return res;
		}
		TdUser user1 = tdUserService.findByUsername(username);
		if (null != user1) {
			res.put("msg", "该用户名已被注册！");
			return res;
		}
		TdUser user2 = tdUserService.findByMobile(mobile);
		if (null != user2) {
			res.put("msg", "该联系电话已被注册！");
			return res;
		}
		if(null != enterType && !enterType.equals("") && (null == enterName ||enterName.equals("")))
		{
			res.put("msg", "公司名称不能为空！");
			return res;
		}

		
		TdUser user = new TdUser();
		user.setUsername(username);
		user.setMobile(mobile);
		user.setStatusId(1L);
		user.setRegisterTime(new Date());
		user.setRoleId(roleId);
		user.setLastLoginTime(new Date());
		tdUserService.save(user);
		
        Long id = user.getId();
        tdUserService.save(user);
        
		
		request.getSession().setAttribute("username", user.getUsername());
		
	    res.put("code", 0);
	    return res;

	}
	


	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(0|86|17951|[0-9]{3})?([0-9]{8})|((13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8})$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
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