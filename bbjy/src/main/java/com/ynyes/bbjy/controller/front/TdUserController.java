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
import com.ynyes.bbjy.entity.TdFamily;
import com.ynyes.bbjy.entity.TdManager;
import com.ynyes.bbjy.entity.TdManagerRole;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.entity.TdUserItem;
import com.ynyes.bbjy.service.TdAreaService;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdFamilyService;
import com.ynyes.bbjy.service.TdManagerRoleService;
import com.ynyes.bbjy.service.TdManagerService;
import com.ynyes.bbjy.service.TdUserItemService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.ClientConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import scala.concurrent.duration.FiniteDuration;

/**
 * 客户中心
 * 
 * @author Max
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
	
	@Autowired
	private TdManagerService tdManagerService;
	
	@Autowired
	private TdFamilyService tdFamilyService;
	
	@Autowired
	private TdUserItemService tdUserItemService;
	
	@Autowired
	private TdManagerRoleService tdManagerRoleService;

	/**
	 * 客户页面查看
	 * 
	 */
	@RequestMapping(value = "/user/{statusId}/{timeId}/{item}")
	public String user(@PathVariable Long statusId,
						@PathVariable Long timeId,
						@PathVariable Long item,
						Integer page,
						HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if(null == username){
//			return "redirect:http://www.probay.com.cn";
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(username);
		TdManagerRole managerRole = tdManagerRoleService.findOne(manager.getRoleId());
		
		if(null == statusId){
			statusId = 0L;
		}
		
		if(null == timeId){
			timeId = 0L;
		}
		
		if(null == item){
			item = 0L;
		}
		
		if(null == page || page < 0)
		{
			page = 0;
		}
		if(null != managerRole){
			map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));
		}else{
			map.addAttribute("manager_list", tdManagerService.findAll());
		}
		
		map.addAttribute("page", page);
		map.addAttribute("statusId", statusId);
		map.addAttribute("timeId", timeId);
		map.addAttribute("item", item);
		map.addAttribute("active", 3L);
		
		tdCommonService.setHeader(map, req);
		if(null == managerRole || managerRole.getIsSys()){
			map.addAttribute("potential_user", tdUserService.countByStatusId(1L));
			map.addAttribute("follow_user", tdUserService.countByStatusId(2L));
			map.addAttribute("service_user", tdUserService.countByStatusId(3L));
			map.addAttribute("freeze_user", tdUserService.countByStatusId(4L));
			int totalUser = 0;
			List<TdUser> list = tdUserService.findAll();
			if(null != list){
				totalUser = list.size();
			}
			map.addAttribute("total_user",totalUser );
			
			if(statusId==0L && timeId==0L && item==0){
				map.addAttribute("user_page", tdUserService.findAllOrderByLastLoginTimeAsc(page, ClientConstant.pageSize));
			}else if(statusId != 0){
				map.addAttribute("user_page", tdUserService.findByStatusId(statusId, page, ClientConstant.pageSize));
			}else if (timeId != 0L){
				if (timeId == 1L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		          //  calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7); // 天数减7
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByLastLoginTimeBefore(time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 2L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByLastLoginTimeBefore(time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 3L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -3);// 月份减三
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByLastLoginTimeBefore(time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 4L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		          //  calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);// 天数减七
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByLastLoginTimeAfter(time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 5L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByLastLoginTimeAfter(time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}
			}else if(item != 0L){
				if(item==1){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
//		            calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("Item_page", tdUserItemService.findByItemTimeAfter(time,1L, page, ClientConstant.pageSize));
				}else if(item==2){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
//		            calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByItemTimeAfter(time,2L, page, ClientConstant.pageSize));
				}else if(item==3){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByItemTimeAfter(time,1L, page, ClientConstant.pageSize));
				}else if(item==4){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByItemTimeAfter(time,2L, page, ClientConstant.pageSize));
				}
				return "/client/user_item_list";
			}
		}else{
			map.addAttribute("potential_user", tdUserService.countByManagerIdAndStatusId(manager.getId(),1L));
			map.addAttribute("follow_user", tdUserService.countByManagerIdAndStatusId(manager.getId(),2L));
			map.addAttribute("service_user", tdUserService.countByManagerIdAndStatusId(manager.getId(),3L));
			map.addAttribute("freeze_user", tdUserService.countByManagerIdAndStatusId(manager.getId(),4L));
			map.addAttribute("total_user",tdUserService.countByManagerId(manager.getId()));
			
			if(statusId==0L && timeId==0L && item==0){
				map.addAttribute("user_page", tdUserService.findByMangerIdOrderByLastLoginTimeAsc(manager.getId(),page, ClientConstant.pageSize));
			}else if(statusId != 0){
				map.addAttribute("user_page", tdUserService.findByManagerIdAndStatusId(manager.getId(),statusId, page, ClientConstant.pageSize));
			}else if (timeId != 0L){
				if (timeId == 1L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		          //  calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7); // 天数减7
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByManagerIdAndLastLoginTimeBefore(manager.getId(),time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 2L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByManagerIdAndLastLoginTimeBefore(manager.getId(),time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 3L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -3);// 月份减三
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByManagerIdAndLastLoginTimeBefore(manager.getId(),time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 4L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		          //  calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);// 天数减七
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByManagerIdAndLastLoginTimeAfter(manager.getId(),time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}else if (timeId == 5L){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("user_page", tdUserService.findByManagerIdAndLastLoginTimeAfter(manager.getId(),time, page, ClientConstant.pageSize));
		            return "/client/user_time";
				}
			}else if(item != 0L){
				if(item==1){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
//		            calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		            
		            map.addAttribute("Item_page", tdUserItemService.findByManagerIdAndItemTimeAfter(manager.getId(),time,1L, page, ClientConstant.pageSize));
				}else if(item==2){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
//		            calendar.add(Calendar.MONTH, -1);// 月份减一
		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByManagerIdAndItemTimeAfter(manager.getId(),time,2L, page, ClientConstant.pageSize));
				}else if(item==3){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByManagerIdAndItemTimeAfter(manager.getId(),time,1L, page, ClientConstant.pageSize));
				}else if(item==4){
					Date cur = new Date(); 
		            Calendar calendar = Calendar.getInstance();// 日历对象
		            calendar.setTime(cur);// 设置当前日期
		            calendar.add(Calendar.MONTH, -1);// 月份减一
//		            calendar.add(Calendar.DAY_OF_MONTH, -7);
		            Date time = calendar.getTime();
		            time.setHours(0);
		            time.setMinutes(0);
		         
		            map.addAttribute("Item_page", tdUserItemService.findByManagerIdAndItemTimeAfter(manager.getId(),time,2L, page, ClientConstant.pageSize));
				}
				return "/client/user_item_list";
			}
		}
		return "/client/user_center";
	}
	
	/**
	 * 修改信息
	 * 
	 */
	@RequestMapping(value="/user/edit")
	public String userEdit(Long id,HttpServletRequest req,ModelMap map)
	{
		String managerName = (String)req.getSession().getAttribute("username");
		if(null == managerName){
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(managerName);
		if(null == manager)
		{
			return "redirect:/";
		}
		
		if(null != id){
			map.addAttribute("user", tdUserService.findOne(id));
		}
		
		map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));

		return "/client/user_massage";
	}
	
	
	/**
	 * 新增客户
	 */
	@RequestMapping(value="/user/add")
	public String addUser(Long id,
			String username,Long managerid,
			Long statusId,String englishName,
			String idCard,String marital,
			String nationality,String sex,
			String email,String birthday,
			String address,String mailingAddress,
			String nature,String company,
			String workYear,//Double income,
			String position,String comAddress,
			HttpServletRequest req,ModelMap map)
	{
		
		String managerName = (String)req.getSession().getAttribute("username");
		if(null == managerName){
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(managerName);
		if(null == manager)
		{
			return "redirect:/";
		}
		Double income = new Double(0.0);
		
		TdUser tdUser = tdUserService.addNewUser(id, username, managerid, 
											statusId, englishName, idCard, 
											marital, nationality, sex, 
											email, birthday, address,
											mailingAddress, nature,
											company, workYear, income, 
											position, comAddress);
		tdUser.setManager(manager.getRealName());
		
		map.addAttribute("user", tdUser);
		map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));

		return "/client/user_massage";
	}
	
	/**
	 * 新增家庭关系人
	 * 
	 */
	@RequestMapping(value="/user/family/add")
	public String familyAdd(Long userId,Long fId,
								String username,
								Long relation,String englishName,
								String idCard,String marital,
								String nationality,String sex,
								//Date birthday,
								String nature,String company,
								String workYear,//Double income,
								String position,String comAddress,
								HttpServletRequest req,ModelMap map)
	{
		String managerName = (String)req.getSession().getAttribute("username");
		if(null == managerName){
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(managerName);
		if(null == manager)
		{
			return "redirect:/";
		}
		
		List<TdFamily> familyList = new ArrayList<>();
	
		TdUser user = tdUserService.findOne(userId);
		
		if(null != user.getFamilyList())
		{
			familyList= user.getFamilyList();
		}
		
		TdFamily family = new TdFamily();
		if(null != fId){
			family = tdFamilyService.findOne(fId);
		}
		
		family.setUsername(username);
		family.setEnglishName(englishName);
		family.setIdCard(idCard);
		family.setMarital(marital);
		family.setNationality(nationality);
		family.setSex(sex);
		family.setNature(nature);
		family.setCompany(company);
		family.setWorkYear(workYear);
//		family.setIncome(income);
		family.setPosition(position);
		family.setComAddress(comAddress);
		
		family = tdFamilyService.save(family);
		
		familyList.add(family);
		
		user.setFamilyList(familyList);
		
		map.addAttribute("user", tdUserService.save(user));
		map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));
		
		return "/client/user_massage";
	}
	
	/**
	 * 修改关系人
	 */
	@RequestMapping(value="/family/edit")
	public String relationEdit(Long id,Long relation,Long userId,HttpServletRequest req,ModelMap map)
	{
		String managerName = (String)req.getSession().getAttribute("username");
		if(null == managerName){
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(managerName);
		if(null == manager)
		{
			return "redirect:/";
		}
		
		if(null != id)
		{
			TdFamily family = tdFamilyService.findOne(id);
			family.setRelation(relation);
			
			tdFamilyService.save(family);
		}
		
		map.addAttribute("user", tdUserService.findOne(userId));
		map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));
		
		return "/client/user_massage";
	} 
	
	@RequestMapping(value="/Item/add")
	public String ItemAdd(Long userId,String info,String ItemTime,
			String theme,
					HttpServletRequest req,ModelMap map){
		String managerName = (String)req.getSession().getAttribute("username");
		if(null == managerName){
			return "redirect:/Verwalter/login";
		}
		TdManager manager = tdManagerService.findByUsernameAndIsEnableTrue(managerName);
		if(null == manager)
		{
			return "redirect:/";
		}
		
		List<TdUserItem> ItemList = new ArrayList<>();
		
		TdUser user = tdUserService.findOne(userId);
		
		if(null != user.getFamilyList())
		{
			ItemList= user.getItemLIst();
		}
		
		
		TdUserItem item = new TdUserItem();
		
		item.setUsername(user.getUsername());
		item.setuId(user.getId());
		item.setCreateTime(new Date());
//		item.setImagesUrl(manager.getImageUrl);
		item.setManager(manager.getRealName());
		item.setManagerId(manager.getId());
		item.setStatusId(1L);
		item.setStatus(2L); // 其他提醒
		item.setInfo(info);
		item.setTheme(theme);
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
			Date date=sdf.parse(ItemTime);
			item.setItemTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		item = tdUserItemService.save(item);
		
		ItemList.add(item);
		user.setItemLIst(ItemList);
		user.setLastLoginTime(new Date()); // 更新最后联系时间
		
		map.addAttribute("user", tdUserService.save(user));
		map.addAttribute("manager_list", tdManagerService.findByRoleId(manager.getRoleId()));
		return "/client/user_item";
	}
	
	@RequestMapping(value="/Item/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> editItemStatusId(Long id,Long statusId,HttpServletRequest req){
		Map<String,Object> res = new HashMap<>();
		res.put("code", 0);
		System.err.println("edit");
		if(null != id && null != statusId){
			TdUserItem userItem = tdUserItemService.findOne(id);
			userItem.setStatusId(statusId);
			tdUserItemService.save(userItem);
			res.put("code", 1);
		}
		return res;
	}
	
	
	@ModelAttribute
    public void getModel(@RequestParam(value = "id", required = false) Long id,
                        Model model) {
        if (null != id) {
            model.addAttribute("tdUser", tdUserService.findOne(id));
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	// -----------------------------------------------------------------------
	
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


//	@RequestMapping(value = "/user/comment/sec")
//	public String commentSec(HttpServletRequest req, Long commentId, ModelMap map) {
//		List<TdManager> list = tdManagerService.findAll();
//		
//		StringBuffer str = new StringBuffer();
//		for (int i = 0; i < list.size(); i++) {
//			TdManager manager = list.get(i);
//			if(i==0){
//				str.append("[{'id':'"+manager.getId() +"','Price':'158'},");
//			}else if(i == list.size()-1){
//				str.append("{'id':'"+manager.getId() +"','Price':'158'}]");
//			}else{
//				str.append("{'id':'"+manager.getId() +"','Price':'158'},");
//			}
//		}
////		System.err.println(str.toString());
//		JSONArray json = JSONArray.fromObject(str.toString());
//		
//		System.err.println(json);
//		return "/client/comment_sec";
//	}



//	@RequestMapping("/user/check/oldpassword")
//	@ResponseBody
//	public Map<String, Object> checkOldPassword(HttpServletRequest req, String param) {
//		Map<String, Object> res = new HashMap<>();
//		res.put("status", "n");
//
//		String username = (String) req.getSession().getAttribute("username");
//		TdUser user = tdUserService.findByUsername(username);
//		if (!param.equals(user.getPassword())) {
//			res.put("info", "当前密码输入错误！");
//			return res;
//		}
//		res.put("status", "y");
//		return res;
//	}



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

//	@RequestMapping(value = "/user/password", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> userPassword(HttpServletRequest req, String oldPassword, String newPassword,
//			ModelMap map) {
//		Map<String, Object> res = new HashMap<String, Object>();
//		res.put("code", 1);
//
//		String username = (String) req.getSession().getAttribute("username");
//
//		if (null == username) {
//			res.put("msg", "请先登录！");
//			return res;
//		}
//
//		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
//
//
//		map.addAttribute("user", tdUserService.save(user));
//
//		res.put("code", 0);
//		return res;
//	}
//
//	@RequestMapping("/user/password/save")
//	public String savePassword(HttpServletRequest req, String newPassword) {
//		String username = (String) req.getSession().getAttribute("username");
//		TdUser user = tdUserService.findByUsername(username);
//		user.setPassword(newPassword);
//		tdUserService.save(user);
//		
//		return "/client/login";
//	}
	
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
