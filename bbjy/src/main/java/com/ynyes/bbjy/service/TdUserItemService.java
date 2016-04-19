package com.ynyes.bbjy.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.bbjy.entity.TdUserItem;
import com.ynyes.bbjy.repository.TdUserItemRepo;

/**
 * 客户事项服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdUserItemService {

	@Autowired
	private TdUserItemRepo repository;
	
	public TdUserItem save(TdUserItem e)
	{
		if(null == e)
		{
			return null;
		}
		return repository.save(e);
	}
	
	public TdUserItem findOne(Long id){
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
	public Page<TdUserItem> findByItemTimeAfter(Date itemTime,Long status,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByItemTimeAfterAndStatus(itemTime, status, pageRequest);
	}
	
	public Page<TdUserItem> findByManagerIdAndItemTimeAfter(Long managerId,Date itemTime,Long status,int page,int size)
	{
		PageRequest pageRequest = new PageRequest(page, size);
		return repository.findByItemTimeAfterAndStatusAndManagerId(itemTime, status,managerId, pageRequest);
	}
	
}
