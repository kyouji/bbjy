package com.ynyes.bbjy.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.bbjy.entity.TdFamily;

/**
 * TdFamily 数据库操作接口
 * 
 * @author Max
 *
 */
public interface TdFamilyRepo extends
		PagingAndSortingRepository<TdFamily, Long>,
		JpaSpecificationExecutor<TdFamily>{

	
}
