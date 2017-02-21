package com.bin.service.Impl;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.bin.dao.categoryDao;
import com.bin.dao.Impl.categoryDaoImpl;
import com.bin.pojo.category;
import com.bin.service.categoryService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class categoryServiceImpl implements categoryService{
	 
	@Override
	public List<category> findAll() throws Exception {
      CacheManager cm=CacheManager.create(categoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		
		//2.获取指定的缓存
		Cache cache = cm.getCache("categoryCache");
		
		//3.通过缓存获取数据  将cache看成一个map即可
		Element element = cache.get("clist");
		
		List<category> list=null;
		
		//4.判断数据
		if(element==null){
			//从数据库中获取
			//categoryDao cd=(categoryDao) BeanFactory.getBean("categoryDao");
			categoryDao cd=new categoryDaoImpl() ;
			list=cd.findAll();
			
			//将list放入缓存
			cache.put(new Element("clist", list));
			
			System.out.println("缓存中没有数据,已去数据库中获取");
		}else{
			//直接返回
			list=(List<category>) element.getObjectValue();
			
			System.out.println("缓存中有数据");
		}
		
		return list;

}
	}
