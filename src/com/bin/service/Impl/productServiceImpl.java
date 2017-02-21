package com.bin.service.Impl;

import java.util.List;

import com.bin.dao.productDao;
import com.bin.dao.Impl.productDaoImpl;
import com.bin.pojo.PageBean;
import com.bin.pojo.product;
import com.bin.service.productService;

public class productServiceImpl implements productService {

	@Override
	public List<product> findnew() throws Exception {
		productDao pdao=new productDaoImpl();
		return pdao.findnew();
	}

	@Override
	public List<product> findhot() throws Exception {
		productDao pdao=new productDaoImpl();
		return pdao.findhot();
	}

	@Override
	public product getBypid(String pid) throws Exception {
		productDao pdao=new productDaoImpl();
		return pdao.getBypid(pid);
	}

	@Override
	public PageBean<product> findByPage(int currPage, int pageSize, String cid) throws Exception {
		//当前页数
		productDao pdao=new productDaoImpl();
		 List<product> list=pdao.findByPage(currPage, pageSize, cid);
		//总条数
		 int totalCount=pdao.findtotalCount(cid);
		return new PageBean<>(list, currPage, pageSize, totalCount);
	}

}
