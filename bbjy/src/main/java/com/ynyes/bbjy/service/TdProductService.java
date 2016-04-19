package com.ynyes.bbjy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.bbjy.entity.TdProduct;
import com.ynyes.bbjy.repository.TdProductRepo;

/**
 * 产品服务类
 * @author Max
 *
 */
@Service
@Transactional
public class TdProductService {

	@Autowired
	private TdProductRepo repostiry;
	
	
	public TdProduct save(TdProduct e){
		if(null == e){
			return null;
		}
		return repostiry.save(e);
	}
	
	public List<TdProduct> save(List<TdProduct> entities)
	{
		return (List<TdProduct>) repostiry.save(entities);
	}
	
	public TdProduct findOne(Long id){
		if(null == id){
			return null;
		}
		return repostiry.findOne(id);
	}
	
	public void delete(Long id)
	{
		if(null != id){
			repostiry.delete(id);
		}
	}
}
