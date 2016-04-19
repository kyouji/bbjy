package com.ynyes.bbjy.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.bbjy.entity.TdProduct;


/**
 * 产品的数据库操作接口
 * @author Max
 *
 */
public interface TdProductRepo extends 
	PagingAndSortingRepository<TdProduct, Long>,
	JpaSpecificationExecutor<TdProduct>{
	

}
