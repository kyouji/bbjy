package com.ynyes.bbjy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.bbjy.entity.TdArticleData;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdArticleDataRepo extends
		PagingAndSortingRepository<TdArticleData, Long>,
		JpaSpecificationExecutor<TdArticleData> 
{ 
    Page<TdArticleData> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);

   

}