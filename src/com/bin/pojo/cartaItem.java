package com.bin.pojo;

import java.io.Serializable;

public class cartaItem implements Serializable {
			private product p;
			private Integer count;
			private Double subtotal=0.0;
			public product getP() {
				return p;
			}
			public void setP(product p) {
				this.p = p;
			}
			public Integer getCount() {
				return count;
			}
			public void setCount(Integer count) {
				this.count = count;
			}
			public Double getSubtotal() {
				return p.getShop_price()*count;
			}
			public cartaItem(product p, Integer count) {
				super();
				this.p = p;
				this.count = count;
			}
			public cartaItem() {
			}
			
}
