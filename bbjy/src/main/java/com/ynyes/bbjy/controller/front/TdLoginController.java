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
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.VerifServlet;

import net.sf.json.JSONObject;

/**
 * 登录
 *
 */
@Controller
public class TdLoginController {
	@Autowired
	private TdUserService tdUserService;

	@Autowired
	private TdCommonService tdCommonService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginReturn(HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");

		String referer = req.getHeader("referer");

		// 基本信息
		tdCommonService.setHeader(map, req);

		if (null == username) {
			return "/client/login";
		}

		if (null == referer) {
			referer = "/";
		}
		/**
		 * @author lc
		 * @注释：同盟店登录
		 */
		String diysiteUsername = (String) req.getSession().getAttribute("diysiteUsername");

		TdUser tdUser = tdUserService.findByUsername(diysiteUsername);
		if (null != tdUser) {
			if (null != tdUser.getRoleId() && tdUser.getRoleId().equals(2L)) {
				return "redirect:/user/diysite/order/list/0";
			}
		}

		return "redirect:" + referer;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, 
			 HttpServletRequest request) {

		/**
		 * 按账号查找登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		TdUser user = tdUserService.findByUsername(username);

		if (null != user) {
			if (!user.getPassword().equals(password)) {
				return "redirect:http://www.probay.com.cn/";
			}
			
			user.setLastLoginTime(new Date());
			user = tdUserService.save(user);

			request.getSession().setMaxInactiveInterval(60 * 60 * 2);
			
			request.getSession().setAttribute("username", user.getUsername());
			return "redirect:/";
		}
		/**
		 * 如果账号验证未通过，再进行手机登录验证 密码验证 修改最后登录时间
		 * 
		 * @author libiao
		 */
		user = tdUserService.findByMobileAndIsEnabled(username);
		if (null != user) {
			if (!user.getPassword().equals(password)) {
				
				return "redirect:http://www.probay.com.cn/";
			}
			user.setLastLoginTime(new Date());

			user = tdUserService.save(user);



			request.getSession().setAttribute("username", user.getUsername());
			return "redirect:/";
		} else { // 账号-手机都未通过验证，则用户不存在
			return "redirect:http://www.probay.com.cn/";
		}
	}
	
	/**
	 * @author lc
	 * @return
	 * @注释：密码找回
	 */
	@RequestMapping(value = "/user/password/retrieve", method = RequestMethod.GET)
	public String passwordRetrieve(HttpServletRequest req, ModelMap map) {
		tdCommonService.setHeader(map, req);
		return "/client/user_password_retrieve";
	}
	
	@RequestMapping(value = "/user/password/change", method = RequestMethod.GET)
	public String passwordChange(HttpServletRequest req, ModelMap map) {
        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	return "redirect:/login";
        }
        TdUser user = tdUserService.findByUsername(username);
        if (null != user)
        {
        	map.addAttribute("user", user);
        }
		tdCommonService.setHeader(map, req);
		return "/client/user_password_change";
	}
	
	@RequestMapping(value = "/user/password/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userPasswordSubmit(String password, String password2, 
			 HttpServletRequest req) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);
		
        String username = (String) req.getSession().getAttribute("username");
        if(null == username)
        {
        	res.put("login", 1);
        	return res;
        }
		
        if(null == password || password.equals("") || null == password2 || password2.equals(""))
        {
        	res.put("msg", "请填写新密码");
        	return res;
        }
        if(!password.equals(password2))
        {
        	res.put("msg", "亲！两次密码不一致请重新输入");
        	return res;
        }
        
        TdUser user = tdUserService.findByUsername(username);
        user.setPassword(password);
        tdUserService.save(user);
        
		res.put("code", 0);
		return res;
	}

	@RequestMapping(value = "/user/retrieve/submit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Check(String mobile, String code, HttpServletRequest request) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("code", 1);

		String codeBack = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
		if (!codeBack.equalsIgnoreCase(code)) {
			res.put("msg", "验证码错误");
			return res;
		}

		TdUser user = tdUserService.findByMobileAndIsEnabled(mobile);
		if (null != user) {
			request.getSession().setMaxInactiveInterval(60 * 60 * 2);
			request.getSession().setAttribute("username", user.getUsername());

			res.put("code", 0);
		} else {
			res.put("msg", "用户不存在");
		}

		return res;
	}

	
	
	@RequestMapping(value = "/login/retrieve_step2", method = RequestMethod.POST)
	public String Step2(String smsCode,String mobile,HttpServletResponse resp, HttpServletRequest req, ModelMap map) {
		tdCommonService.setHeader(map, req);
		String code = (String)req.getSession().getAttribute("SMSCODE");
		
		map.addAttribute("mobile", mobile);
		if(null !=smsCode){
			if(!smsCode.equalsIgnoreCase(code)){
//				smsCode(mobile, resp, req);
				map.addAttribute("msg","短信验证码错误！");
				return "/client/user_retrieve_step2";
			}
		}
		TdUser user = tdUserService.findByMobile(mobile);
		map.addAttribute("user",user);
		req.getSession().setAttribute("retrieve_username", user.getUsername());
		return "/client/user_retrieve_step3";
	}


	@RequestMapping(value = "/login/retrieve_step3", method = RequestMethod.POST)
	public String Step3(String password, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("retrieve_username");
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		user.setPassword(password);
		tdUserService.save(user);
//		if (1L == user.getRoleId()) {
//			TdEnterprise enterprise = tdEnterpriseService.findbyUsername(username);
//			enterprise.setPassword(password);
//			tdentErpriseService.save(enterprise);
//		}
//		if (2L == user.getRoleId()) {
//			TdRegionAdmin regionAdmin = tdRegionAdminService.findbyUsername(username);
//			regionAdmin.setPassword(password);
//			tdRegionAdminService.save(regionAdmin);
//		}
//		if (3L == user.getRoleId()) {
//			TdExpert expert = tdExpertService.findbyUsername(username);
//			expert.setPassword(password);
//			tdExpertService.save(expert);
//		}
//		if (4L == user.getRoleId()) {
//			TdActivityAdmin activityAdmin = tdActivityAdminService.findbyUsername(username);
//			activityAdmin.setPassword(password);
//			tdActivityAdminService.save(activityAdmin);
//		}
		return "/client/user_retrieve_step4";
	}

	/**
	 * @author lc
	 * @注释：登录手机验证
	 */
	@RequestMapping(value = "/login/mobile_accredit", method = RequestMethod.POST)
	public String mobileVerification(String username, String mobile, String type, String typeId,
			HttpServletRequest request, ModelMap map) {
		if (null == username) {
			return "client/error_404";
		}
		if (null == mobile) {
			return "client/error_404";
		}
		TdUser user = tdUserService.addNewUser(username, "123456", null, null, null);
		if (null != user) {
			// if("qq".equals(type)){
			// //QQ登录新建账号
			// user.setQqUserId(typeId);
			// }else{
			// //支付宝登录新建账号
			// user.setAlipayUserId(typeId);
			// }
			user.setMobile(mobile);
			user.setLastLoginTime(new Date());
			tdUserService.save(user);
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("usermobile", user.getMobile());
			return "redirect:/";
		}
		return "client/error_404";
	}

	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:http://www.probay.com.cn/";
	}

	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public void verify(HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		VerifServlet randomValidateCode = new VerifServlet();
		randomValidateCode.getRandcode(request, response);
	}
	

	
}
