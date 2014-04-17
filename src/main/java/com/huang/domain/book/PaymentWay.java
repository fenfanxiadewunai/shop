package com.huang.domain.book;

public enum PaymentWay {
	NET{
		@Override
		public String getName() {
			return "网上支付";
		}
	},
	COD{
		@Override
		public String getName() {
			return "货到付款";
		}
	},
	BANKREMITTANCE{
		@Override
		public String getName() {
			return "银行电汇";
		}
	},
	POSTOFFICEREMITTANCE{
		@Override
		public String getName() {
			return "邮局汇款";
		}
	};
	
	public abstract String getName();
	
}
