package com.bin.dao;

import com.bin.pojo.order;
import com.bin.pojo.orderItem;

public interface orderDao {

	void add(order order) throws Exception;

	void addItem(orderItem oi)throws Exception;

}
