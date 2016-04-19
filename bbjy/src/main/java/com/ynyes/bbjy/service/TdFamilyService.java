package com.ynyes.bbjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.bbjy.entity.TdFamily;
import com.ynyes.bbjy.repository.TdFamilyRepo;

/**
 * TdFamily服务类 
 * @author Max
 *
 */

@Service
@Transactional
public class TdFamilyService {
	
	@Autowired
	private TdFamilyRepo repository;
	
	
	
	
	public TdFamily save(TdFamily e)
	{
		if(null == e){
			return null;
		}
		return repository.save(e);
	}
	
	public TdFamily findOne(Long id)
	{
		if(null == id){
			return null;
		}
		return repository.findOne(id);
	}
	
}
