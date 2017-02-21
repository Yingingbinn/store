package com.bin.service;

import java.util.List;

import com.bin.pojo.PageBean;
import com.bin.pojo.product;

public interface productService {

	List<product> findnew()throws Exception;

	List<product> findhot() throws Exception;

	product getBypid(String pid)throws Exception ;

	PageBean<product> findByPage(int currPage, int pageSize, String cid) throws Exception;

}
