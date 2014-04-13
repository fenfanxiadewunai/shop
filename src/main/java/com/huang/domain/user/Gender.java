package com.huang.domain.user;

public enum Gender {
	MAN{
		@Override
		public String getName() {
			return "男";
		}
	},
	WOMEN{
		@Override
		public String getName() {
			return "女";
		}
	};
	public abstract String getName();
}
