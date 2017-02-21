package com.bin.service.Impl;

import java.sql.SQLException;

import com.bin.dao.orderDao;
import com.bin.pojo.order;
import com.bin.pojo.orderItem;
import com.bin.service.orderService;
import com.bin.utils.BeanFactory;
import com.bin.utils.DataSourceUtils;

public class orderServiceImpl implements orderService {

	@Override
	public void add(order order) throws Exception{
		try {
			//开启事务
			DataSourceUtils.startTransaction();
			orderDao od=(orderDao) BeanFactory.getBean("OrderDao");
			//向oders中添加一条数据
			od.add(order);
			//向oderitem中添加多条数据
			for (orderItem oi : order.getItems()) {
				od.addItem(oi);
			}
			//事务处理
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

}
