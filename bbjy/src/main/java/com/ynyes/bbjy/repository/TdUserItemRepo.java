package com.ynyes.bbjy.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.bbjy.entity.TdUserItem;

/**
 * TdUserItem数据库操作接口
 * @author Max
 *
 */

public interface TdUserItemRepo extends
	PagingAndSortingRepository<TdUserItem, Long>,
	JpaSpecificationExecutor<TdUserItem>{
	
	
	Page<TdUserItem> findByItemTimeAfterAndStatus(Date itemTime,Long status,Pageable page);
	Page<TdUserItem> findByItemTimeAfterAndStatusAndManagerId(Date itemTime,Long status,Long managerId,Pageable page);
	
}
