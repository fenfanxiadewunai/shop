package com.huang.domain.book;

public enum DeliverWay {
	GENERALPOST{
		@Override
		public String getName() {
			return "平邮";
		}
	},
	EXPRESSDELIVERY{
		@Override
		public String getName() {
			return "快递送货上门";
		}
	},
	EXIGENCEEXPRESSDELIVERY{
		@Override
		public String getName() {
			return "加急快递送货上门";
		}
	},
	EMS{
		@Override
		public String getName() {
			return "国内特快专递EMS";
		}
	};
	public abstract String getName();
}
