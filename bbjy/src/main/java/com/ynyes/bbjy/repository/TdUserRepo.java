package com.ynyes.bbjy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.bbjy.entity.TdUser;

/**
 * TdUser 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserRepo extends
		PagingAndSortingRepository<TdUser, Long>,
		JpaSpecificationExecutor<TdUser> 
{
    // 根据角色查找
    Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, Pageable page);
//    Page<TdUser> findByUsernameContainingOrMobileContainingOrNumberContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, Pageable page);
    
//    Page<TdUser> findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrNumberContainingAndRoleIdOrRealNameContainingAndRoleIdOrderByIdDesc(String keywords1, 
//                                                                Long roleId1,
//                                                                String keywords2,
//                                                                Long roleId2,
//                                                                String keyword3,
//                                                                Long roleId3,
//                                                                String keyword4,
//                                                                Long roleId4, 
//                                                                Pageable page);
    
    TdUser findByUsernameAndStatusIdOrUsernameAndStatusId(String username, Long statusId, String username1, Long statusId1);
    
    TdUser findByUsernameAndStatusIdOrUsernameAndStatusIdOrUsernameAndStatusIdIsNull(String username, Long statusId, String username1, Long statusId1 , String username2); //企业忘了加这个，空值也算吧。zhangji
    
    TdUser findByUsernameIgnoreCase(String username);
    
    TdUser findByUsernameAndIdNot(String username, Long id);
    
//    TdUser findByNumber(String number);
    
    TdUser findByMobileAndStatusIdOrMobileAndStatusId(String mobile,Long statusId,String mobile1,Long statusId1);		//手机号已验证查找
    
    TdUser findByMobile(String mobile);		//手机号查找
    
    Page<TdUser> findByStatusId(Long statusId,Pageable page);
    Page<TdUser> findByManagerIdAndStatusId(Long managerId,Long statusId,Pageable page);
    
    Long countByStatusId(Long statusId);
    Long countByManagerIdAndStatusId(Long managerId,Long statusId);
    
    Page<TdUser> findByLastLoginTimeBefore(Date lastTime,Pageable page);
    Page<TdUser> findByLastLoginTimeAfter(Date lastTime,Pageable page);
    
    Page<TdUser> findByManagerIdAndLastLoginTimeBefore(Long managerId,Date lastTime,Pageable page);
    Page<TdUser> findByManagerIdAndLastLoginTimeAfter(Long managerId,Date lastTime,Pageable page);
    
    Page<TdUser> findByBirthdayAfter(Date birthday,Pageable page);
    
    Page<TdUser> findByManagerId(Long managerId,Pageable page);
    
    Long countByManagerId(Long managerId);
      
}
