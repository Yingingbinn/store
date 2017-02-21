package com.bin.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
	// 存放购物车项目的集合
	private Map<String, cartaItem> map = new LinkedHashMap<>();
	// 总金额
	private Double total = 0.0;

	// 获取所有的购物车项
	public Collection<cartaItem> getitems() {
		 return map.values();
	}

	// 添加到购物车
	public void add2cart(cartaItem item) {
		String pid = item.getP().getPid();
		if (map.containsKey(pid)) {
			// 设置数量等于旧的添加商品的数量+新添加的商品的数量(item.getcount())
			cartaItem oitem = map.get(pid);
			oitem.setCount(oitem.getCount() + item.getCount());

		} else {
			map.put(pid, item);
		}
		// 设置总金和
		total += item.getSubtotal();
	}

	// 从购物车中删除
	public void removeFromCart(String pid) {
		cartaItem item = map.remove(pid);
		total -= item.getSubtotal();

	}

	// 清空购物车
	public void clear() {
		map.clear();
		total = 0.0;
	}

	public Map<String, cartaItem> getMap() {
		return map;
	}

	public void setMap(Map<String, cartaItem> map) {
		this.map = map;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
