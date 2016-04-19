package com.ynyes.bbjy.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynyes.bbjy.entity.TdArticle;
import com.ynyes.bbjy.entity.TdArticleCategory;
import com.ynyes.bbjy.entity.TdNavigationMenu;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.service.TdArticleCategoryService;
import com.ynyes.bbjy.service.TdArticleService;
import com.ynyes.bbjy.service.TdCommonService;
import com.ynyes.bbjy.service.TdNavigationMenuService;
import com.ynyes.bbjy.service.TdUserService;
import com.ynyes.bbjy.util.ClientConstant;

/**
 * 
 * 资讯
 *
 */
@Controller
public class TdDataController {
	@Autowired
	private TdArticleService tdArticleService;

	@Autowired
	private TdArticleCategoryService tdArticleCategoryService;

	@Autowired
	private TdCommonService tdCommonService;

	@Autowired
	private TdNavigationMenuService tdNavigationMenuService;

	@Autowired
	private TdUserService tdUserService;

	// 讯息
	@RequestMapping("/data/info")
	public String dataInfo(Long catId, Integer page, ModelMap map, HttpServletRequest req) {
		 String username = (String) req.getSession().getAttribute("username");
		 if (null == username) {
		 return "/client/to_login";
		 }
		 TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		 map.addAttribute("user", user);

		tdCommonService.setHeader(map, req);

		if (null == page) {
			page = 0;
		}

		Integer size = ClientConstant.infoSize;

		// 新闻动态所有类别
		List<TdArticleCategory> tdArticleCategories = tdArticleCategoryService.findByMenuId(8L);
		if (null != tdArticleCategories) {
			map.addAttribute("cat_list", tdArticleCategories);

			if (null == catId) {
				catId = tdArticleCategories.get(0).getId();
			}

			TdArticleCategory present = tdArticleCategoryService.findOne(catId);
			if (null != present) {
				if (null != present.getInfoType()) {
					map.addAttribute("infoType", present.getInfoType());
				}

				String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
				Calendar cal = Calendar.getInstance();
				Page<TdArticle> article_page = null;
				if(catId.equals(3L)){
					article_page = tdArticleService.findByMenuIdAndCategoryIdAndIsEnable(8L,catId, page, size);
				}else{
				    article_page = tdArticleService.findByMenuIdAndCategoryIdAndIsEnableOrderBySortId(8L,catId, page, size);
				}
				
				if (null != article_page && null != article_page.getContent() && article_page.getContent().size() > 0) {
					for (int i = 0; i < article_page.getContent().size(); i++) {
						TdArticle article = article_page.getContent().get(i);
						if (null != article && null != article.getDate()) {
							// 获取当前的年份
							int year = cal.get(Calendar.YEAR);
							// 格式化article中的date（MM-dd）
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
							String month_day = sdf.format(article.getDate());
							// 拼接日期
							String y_m_d = year + "-" + month_day;
							// 格式化拼接的日期
							SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date real_date = ymd.parse(y_m_d);
								cal.setTime(real_date);
								int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
								if (week < 0) {
									week = 0;
								}
								map.addAttribute("week" + i, weekDays[week]);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
				map.addAttribute("info_page", article_page);
			}

			map.addAttribute("catId", catId);
			map.addAttribute("catTitle", present.getTitle());
		}

		Long active = 2L;
		map.addAttribute("active", active);
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		map.addAttribute("mid", 8);

		return "/client/data_info";
	}

	// 行销资源
	@RequestMapping("/data/source")
	public String dataSource(Long catId, Integer page, ModelMap map, HttpServletRequest req) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "/client/to_login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		map.addAttribute("user", user);

		tdCommonService.setHeader(map, req);

		if (null == page) {
			page = 0;
		}

		Integer size = ClientConstant.infoSize;

		// 新闻动态所有类别
		List<TdArticleCategory> tdArticleCategories = tdArticleCategoryService.findByMenuIdAndParentId(11L, 0L);
		if (null != tdArticleCategories) {
			map.addAttribute("cat_list", tdArticleCategories);

			for (TdArticleCategory category : tdArticleCategories) {
				List<TdArticleCategory> subCategories = tdArticleCategoryService.findByMenuIdAndParentId(11L,
						category.getId());
				map.addAttribute("sub_" + category.getId(), subCategories);
			}

			if (null == catId) {
				catId = tdArticleCategories.get(0).getId();
			}

			TdArticleCategory present = tdArticleCategoryService.findOne(catId);
			if (null != present) {
				if (null != present.getInfoType()) {
					map.addAttribute("infoType", present.getInfoType());
				}
				Page<TdArticle> info = tdArticleService.findByMenuIdAndCategoryIdAndIsEnable(11L, catId,
						page, size);
				map.addAttribute("info_page",
						tdArticleService.findByMenuIdAndCategoryIdAndIsEnable(11L, catId, page, size));
			}

			if (null != present.getParentId() && present.getParentId() != 0) {
				map.addAttribute("parentId", present.getParentId());
			} else {
				map.addAttribute("parentId", present.getId());
			}

			map.addAttribute("catId", catId);
			map.addAttribute("catTitle", present.getTitle());
		}

		Long active = 5L;
		map.addAttribute("active", active);
		map.addAttribute("page", page);
		map.addAttribute("size", size);
		map.addAttribute("mid", 11);

		return "/client/data_source";
	}

	@RequestMapping(value = "/data/detail", method = RequestMethod.POST)
	public String cartNumberAdd(Long id, HttpServletRequest req, ModelMap map) {
		String username = (String) req.getSession().getAttribute("username");
		if (null == username) {
			return "/client/to_login";
		}
		TdUser user = tdUserService.findByUsernameAndIsEnabled(username);
		map.addAttribute("user", user);

		if (null != id) {
			TdArticle tdArticle = tdArticleService.findOne(id);
			if (null != tdArticle) {
				map.addAttribute("tdArticle", tdArticle);

				// 类别标题
				TdArticleCategory cat = tdArticleCategoryService.findOne(tdArticle.getCategoryId());
				TdNavigationMenu menu = tdNavigationMenuService.findOne(tdArticle.getMenuId());
				map.addAttribute("menuName", menu.getTitle() + "-" + cat.getTitle());

				if (null != menu && menu.getId() == 11L) {
					return "/client/data_source_detail";
				}
			}
		}

		return "/client/data_detail";
	}

}
