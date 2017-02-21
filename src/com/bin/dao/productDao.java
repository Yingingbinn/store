package com.bin.dao;

import java.util.List;

import com.bin.pojo.product;

public interface productDao {

	List<product> findnew() throws Exception;

	List<product> findhot() throws Exception;

	product getBypid(String pid) throws Exception;


	int findtotalCount(String cid) throws Exception;

	List<product> findByPage(int currPage, int pageSize, String cid) throws Exception;

	

	

}
