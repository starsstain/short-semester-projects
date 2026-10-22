package com.ccbupt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccbupt.dao.HouseDaoI;
import com.ccbupt.entity.HouseEntity;
import com.ccbupt.entity.R;
import com.ccbupt.entity.UserEntity;
@Service
public class HouseService {
	@Autowired
	HouseDaoI hdi;
	
	public R<HouseEntity>  save(HouseEntity hd) {
		HouseEntity dbhd=hdi.getByCode(hd.getHousecode())	;
	    if(null==dbhd) {
	    	hdi.save(hd);
	    	 return R.ok(null);
	    }else
	    {
	    	return R.fail("该房屋编码已存在！");
	    }		
	}
 	public int delete(int id){
		return hdi.delete(id);
	}
 	public R<HouseEntity> update(HouseEntity hd){
		 hdi.update(hd);
		 return R.ok(null);
	}
 	public List<HouseEntity>getAllList(){
		return hdi.getAllList();
	}
 	public HouseEntity getById(int id) {
		return hdi.getById(id);
	}
 	public List<HouseEntity>getListByHousename(String housename){
		return hdi.getListByHousename(housename);
	}

}


