package com.huang.domain.book;

public enum OrderState {
	CANCEL{
		@Override
		public String getName() {
			return "已取消";
		}
	},
	WAITCONFIRM{
		@Override
		public String getName() {
			return "待审核";
		}
	},
	WAITPAYMENT{
		@Override
		public String getName() {
			return "等待付款";
		}
	},
	ADMEASUREPRODUCT{
		@Override
		public String getName() {
			return "正在配货";
		}
	},
	WAITDELIVER{
		@Override
		public String getName() {
			return "等待发货";
		}
	},
	DELIVERD{
		@Override
		public String getName() {
			return "已发货";
		}
	},
	RECEIVED{
		@Override
		public String getName() {
			return "已收货";
		}
	};
	public abstract String getName();
}
