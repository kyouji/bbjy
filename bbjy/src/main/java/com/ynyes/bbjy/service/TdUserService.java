package com.ynyes.bbjy.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.bbjy.entity.TdManager;
import com.ynyes.bbjy.entity.TdUser;
import com.ynyes.bbjy.entity.TdUserItem;
import com.ynyes.bbjy.repository.TdUserRepo;

/**
 * TdUser 服务类
 * 
 * @author Max
 *
 */

@Service
@Transactional
public class TdUserService {
	@Autowired
	TdUserRepo repository;

	@Autowired
	TdSettingService tdSettingService;
	
	@Autowired
	TdManagerService tdManagerService;
	
	@Autowired
	TdUserItemService tdUserItemService;

	/**
	 * 删除用户数据
	 * 
	 * @param username
	 */
	private void userDataDelete(String username) {
		if (null == username) {
			return;
		}

	}

	public TdUser addNewUser(Long id,
			String username,Long managerid,
			Long statusId,String englishName,
			String idCard,String marital,
			String nationality,String sex,
			String email,String birthday,
			String address,String mailingAddress,
			String nature,String company,
			String workYear,Double income,
			String position,String comAddress) {
		
		TdUser user = new TdUser();
		if(null != id){
			user = repository.findOne(id);
		}

		user.setUsername(username);
		user.setManagerId(managerid);
		user.setStatusId(statusId);
		user.setEnglishName(englishName);
		user.setIdCard(idCard);
		user.setMarital(marital);
		user.setNationality(nationality);
		user.setSex(sex);
		user.setEmail(email);
		user.setAddress(comAddress);
		user.setMailingAddress(mailingAddress);
		user.setNature(nature);
		user.setCompany(company);
		user.setWorkYear(workYear);
		user.setIncome(income);
		user.setPosition(position);
		user.setComAddress(comAddress);
		
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
			Date date=sdf.parse(birthday);
			user.setBirthday(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(null == user.getRegisterTime())
		{
			user.setRegisterTime(new Date());
		}
		user.setLastLoginTime(new Date());
		if(null == user.getRoleId())
		{
			user.setRoleId(1L);
		}
		
		if(null == id){
			TdUserItem item = new TdUserItem();
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
				Date date = sdf.parse(birthday);
				Calendar calendar = Calendar.getInstance();// 日历对象
				calendar.setTime(date);// 设置当前日期
				calendar.add(Calendar.YEAR, 2016);
				Date time = calendar.getTime();
//				time.setHours(0);
//				time.setMinutes(0);
				item.setItemTime(time); // 生日提醒时间
				item.setStatus(1L); // 生日提醒
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			item.setUsername(user.getUsername());
			item.setuId(user.getId());
			item.setCreateTime(new Date());
//			item.setImagesUrl(manager.getImageUrl);
			TdManager manager = tdManagerService.findOne(managerid);
			item.setManager(manager.getRealName());
			
			item.setManagerId(managerid);
			item.setStatusId(1L);
			item.setInfo("生日");
			item.setTheme("生日");
			
			tdUserItemService.save(item);
		}
		
		return repository.save(user);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            菜单项ID
	 */
	public void delete(Long id) {
		if (null != id) {
			TdUser user = repository.findOne(id);

			if (null != user) {
				userDataDelete(user.getUsername());
			}

			repository.delete(id);
		}
	}

	/**
	 * 删除
	 * 
	 * @param e
	 *            菜单项
	 */
	public void delete(TdUser e) {
		if (null != e) {

			userDataDelete(e.getUsername());
			repository.delete(e);
		}
	}

	/**
	 * 查找
	 * 
	 * @param id
	 *            ID
	 * @return
	 */
	public TdUser findOne(Long id) {
		if (null == id) {
			return null;
		}

		return repository.findOne(id);
	}
	
//	public TdUser findByNumber(String number) {
//		if (null == number) {
//			return null;
//		}
//
//		return repository.findByNumber(number);
//	}

	public TdUser findByUsernameAndIsEnabled(String username) {
		if (null == username) {
			return null;
		}

		return repository.findByUsernameAndStatusIdOrUsernameAndStatusIdOrUsernameAndStatusIdIsNull(username, 0L, username, 1L , username);
	}

	public TdUser findByUsername(String username) {
		if (null == username) {
			return null;
		}

		return repository.findByUsernameIgnoreCase(username);
	}

	public TdUser findByUsernameAndIdNot(String username, Long id) {
		if (null == username || null == id) {
			return null;
		}

		return repository.findByUsernameAndIdNot(username, id);
	}


	/**
	 * 手机号查找
	 * @return
	 */
	public TdUser findByMobileAndIsEnabled(String mobile) {
		if (null == mobile) {
			return null;
		}
		return repository.findByMobileAndStatusIdOrMobileAndStatusId(mobile, 0L, mobile, 1L);
	}

	/**
	 * 
	 * 注册查找----按手机号
	 * 
	 * @return
	 */
	public TdUser findByMobile(String mobile) {
		if (null == mobile) {
			return null;
		}
		return repository.findByMobile(mobile);
	}



	/**
	 * 查找
	 * 
	 */
	public List<TdUser> findAll(Iterable<Long> ids) {
		return (List<TdUser>) repository.findAll(ids);
	}
	
	public List<TdUser> findAll(){
		return (List<TdUser>) repository.findAll();
	}
	
	public Long countByManagerId(Long managerId)
	{
		if(null == managerId){
			return null;
		}
		return repository.countByManagerId(managerId);
	}

	public Page<TdUser> findAllOrderByLastLoginTimeAsc(int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));

		return repository.findAll(pageRequest);
	}
	
	public Page<TdUser> findByMangerIdOrderByLastLoginTimeAsc(Long managerId,int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));

		return repository.findByManagerId(managerId, pageRequest);
	}

	public Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, int page, int size) {
		PageRequest pageRequest = new PageRequest(page, size);

		return repository.findByRoleIdOrderByIdDesc(roleId, pageRequest);
	}

//	public Page<TdUser> searchAndFindByRoleIdOrderByIdDesc(String keywords, Long roleId, int page, int size) {
//		PageRequest pageRequest = new PageRequest(page, size);
//
//		return repository
//				.findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrNumberContainingAndRoleIdOrRealNameContainingAndRoleIdOrderByIdDesc(
//						keywords, roleId, keywords, roleId, keywords, roleId, keywords , roleId , pageRequest);
//	}

//	public Page<TdUser> searchAndOrderByIdDesc(String keywords, int page, int size) {
//		PageRequest pageRequest = new PageRequest(page, size);
//
//		return repository.findByUsernameContainingOrMobileContainingOrNumberContainingOrderByIdDesc(keywords, keywords,
//				keywords, pageRequest);
//	}

	/**
	 * 保存
	 * 
	 * @param e
	 * @return
	 */
	public TdUser save(TdUser e) {


		return repository.save(e);
	}

	public List<TdUser> save(List<TdUser> entities) {

		return (List<TdUser>) repository.save(entities);
	}
	
	
	public Page<TdUser> findByStatusId(Long statusId,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByStatusId(statusId, pageRequest);
	}
	
	public Page<TdUser> findByManagerIdAndStatusId(Long managerId,Long statusId,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByManagerIdAndStatusId(managerId,statusId, pageRequest);
	}
	
	public Long countByStatusId(Long statusId)
	{
		return repository.countByStatusId(statusId);
	}
	
	public Long countByManagerIdAndStatusId(Long managerId,Long statusId)
	{
		return repository.countByManagerIdAndStatusId(managerId,statusId);
	}
	
	
	/**
	 *  反馈时间
	 */
	public Page<TdUser> findByLastLoginTimeBefore(Date lastTime,int page ,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByLastLoginTimeBefore(lastTime, pageRequest);
	}
	
	public Page<TdUser> findByLastLoginTimeAfter(Date lastTime,int page ,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByLastLoginTimeAfter(lastTime, pageRequest);
	}
	
	public Page<TdUser> findByManagerIdAndLastLoginTimeBefore(Long managerId,Date lastTime,int page ,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByManagerIdAndLastLoginTimeBefore(managerId,lastTime, pageRequest);
	}
	
	public Page<TdUser> findByManagerIdAndLastLoginTimeAfter(Long managerId,Date lastTime,int page ,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		return repository.findByManagerIdAndLastLoginTimeAfter(managerId,lastTime, pageRequest);
	}
	
	public Page<TdUser> findByBirthdayAfter(Date birthday,int page ,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.DESC, "lastLoginTime"));
		
		return repository.findByBirthdayAfter(birthday, pageRequest);
	}
	
	
	
	
	
	
	
	
	
	
	
}
